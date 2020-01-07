package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerPublish_Subscribe_Sms {
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";


    public static void main(String[] args) throws IOException, TimeoutException {


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");// 设置虚拟机
        Connection connection =null;
        Channel channel=null;
        try {
            //建立新连接
            connection=connectionFactory.newConnection();
            // 创建通道
            channel= connection.createChannel();
            /**
                          * 声明队列，如果Rabbit中没有此队列将自动创建
                          * param1:队列名称
                          * param2:是否持久化
                          * param3:队列是否独占此连接
                          * param4:队列不再使用时是否自动删除此队列
                          * param5:队列参数
                          */
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
           //声明交换机
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);


            //实现消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                //当接收到消息时，此方法将被调用

                /**
                 *
                 * @param consumerTag 消费者标签，用来标识消费者
                 * @param envelope 信封，
                 * @param properties
                 * @param body
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchange = envelope.getExchange(); //获取交换机
                    long id = envelope.getDeliveryTag();// 消息id，mq在channel中用来标识消息的id，确认消息已经接收
                    String message = new String(body,"utf-8");
                    System.out.println("接收到消息"+message);

                }
            };

            //监听队列
          /**
                      * 监听队列String queue, boolean autoAck,Consumer callback
                      * 参数明细
                      * 1、队列名称
                      * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置
             为false则需要手动回复
                      * 3、消费消息的方法，消费者接收到消息后调用此方法
                      */
           channel.basicConsume(QUEUE_INFORM_SMS,true,defaultConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
