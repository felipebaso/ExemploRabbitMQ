package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  private final static String QUEUE_NAME = "fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.235.21.200"); 
    factory.setUsername("admin"); 
    factory.setPassword("password");
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String message = "Apenas que... busquem conhecimento - ass: ET Bilu!!!";
    
    channel.queueDeclare(QUEUE_NAME, true,   false,     false,       null);
    channel.basicPublish("",       QUEUE_NAME, null,  message.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + message + "'");
  
  }
}