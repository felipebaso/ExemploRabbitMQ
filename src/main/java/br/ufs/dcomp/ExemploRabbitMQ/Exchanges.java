package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Exchanges {


  private final static String QUEUE_NAME = "queue_";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.235.21.200"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    
    Channel channel = connection.createChannel();
    
    channel.exchangeDeclare("E1", "fanout");
    channel.exchangeDeclare("E2", "direct");
    
    String [] routingKeys= {"A", "B"};
    
    boolean A = true;

    
    for(int i = 1; i<=10; i++){
          channel.queueDeclare(QUEUE_NAME+i, true,   false,     false,       null);
          
          if(i % 2 != 0){
            channel.queueBind(QUEUE_NAME+i, "E1", "");
          }
          
          else {
            if (A){
             channel.queueBind(QUEUE_NAME+i, "E2", routingKeys[0]);
             A = false;
            }
            else{
              channel.queueBind(QUEUE_NAME+i, "E2", routingKeys[1]);
              A = true;
            }
          }

    }
  
  channel.close();
  connection.close();
  
  }
}