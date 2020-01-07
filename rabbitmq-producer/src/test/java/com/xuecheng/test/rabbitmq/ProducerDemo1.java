package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerDemo1 {
    private static  final  String QUEUE="hello,rabbitmq";


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
            channel.queueDeclare(QUEUE,true,false,false,null);
            String message = "hello,xiaoqiang";
            /**
                          * 消息发布方法
                          * param1：Exchange的名称，如果没有指定，则使用Default Exchange
                          * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列
                          * param3:消息包含的属性
                          * param4：消息体
                          */
            channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println("Send Message is:'" + message +"'");

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
