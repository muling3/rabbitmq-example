package com.example.RabbitMq.consumer;

import com.example.RabbitMq.config.PostsQueConfig;
import com.example.RabbitMq.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostsConsumer {

    @RabbitListener(queues = PostsQueConfig.POSTS_QUEUE)
    public void listener(Post post){
        log.info("Received {}", post);
    }
}
