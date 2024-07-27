package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EnvioDirect {


  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.235.21.200"); 
    factory.setUsername("admin"); 
    factory.setPassword("password");
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String message = "atenção DIRECT A";
        channel.basicPublish("E2",   "A"    , null,  message.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + message + "'");
  
  }
}