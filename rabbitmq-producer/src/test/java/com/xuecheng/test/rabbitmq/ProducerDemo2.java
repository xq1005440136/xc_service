package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerDemo2 {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
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
            channel.queueDeclare(QUEUE_INFORM_EMAIL,true,false,false,null);
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
            //声明一个交换机
            /**
             * 参数明细
             * 1:交换机的 名称
             * 2：交换机的类型
             * fanout：对应rabbitmq工作模式是publish/subscribe（发布订阅）
             * direct：对应routing工作模式
             * topic：对应topic工作模式
             * header:对应header工作模式
             */
           channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);

           //绑定队列
            /**
             * 参数明细
             * 1：队列名称
             * 2：交换机名称
             * 3：routingkey；路由key，交换机会根据路由key的值将消息转发到对应的队列
             */
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_FANOUT_INFORM,"");
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,"");

            String infoMessage = "支付成功";
            /**
                          * 消息发布方法
                          * param1：Exchange的名称，如果没有指定，则使用Default Exchange
                          * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
                          * param3:消息包含的属性
                          * param4：消息体
                          */
            for (int i = 0; i < 5; i++) {
                channel.basicPublish(EXCHANGE_FANOUT_INFORM,"",null,infoMessage.getBytes());
                System.out.println("Send Message is:'" + infoMessage +"'");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
           if(channel!=null){
               channel.close();
           }
           if(connection!=null){
               connection.close();
           }
        }

    }
}
