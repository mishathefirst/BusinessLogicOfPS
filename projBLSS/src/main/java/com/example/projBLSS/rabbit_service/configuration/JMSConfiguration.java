package com.example.projBLSS.rabbit_service.configuration;

import com.example.projBLSS.rabbit_service.consuming.ConsumerMessageListener;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.jms.*;


@Profile("stats")
public class JMSConfiguration {


//    @Bean
//    public ConnectionFactory jmsConnectionFactory() {
       RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setHost("localhost");
//        connectionFactory.setPort(5672);
//        return connectionFactory;
//    }

//    @Bean
//    public Destination jmsDestination() {
//        RMQDestination jmsDestination = new RMQDestination();
//        jmsDestination.setDestinationName("likes1");
//        jmsDestination.setAmqp(true);
//        jmsDestination.setAmqpQueueName("likes1");
//        return jmsDestination;
//    }

//    @Bean
//    public ConsumerMessageListener messageListener(){
//        return new ConsumerMessageListener();
//    }

//    @Bean
//    public JMSConsumer jmsConsumer() throws JMSException {
//        Connection connection = jmsConnectionFactory().createConnection();
//        JMSConsumer consumer = jmsConnectionFactory().createContext().createConsumer(jmsDestination());
//        consumer.setMessageListener(messageListener());
//        connection.start();
//        return consumer;
//    }

}
