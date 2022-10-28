package com.example.RabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostsQueConfig {

    public static final String POSTS_QUEUE = "posts_queue";
    public static final String POSTS_TOPIC = "posts_topic";
    public static final String ROUTING_KEY = "posts_key";

    //creating the queue
    @Bean
    public Queue queue(){
        return new Queue(POSTS_QUEUE);
    }

    //creating a topic
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(POSTS_TOPIC);
    }


    //binding the queue together with the topic using a routing key
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    //create a json message converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}
