package com.example.RabbitMq.publisher;

import com.example.RabbitMq.config.PostsQueConfig;
import com.example.RabbitMq.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostPublisher {
    @Autowired
    public RabbitTemplate template;

    @PostMapping
    public String publishPost(@RequestBody Post post){
        template.convertAndSend(PostsQueConfig.POSTS_TOPIC, PostsQueConfig.ROUTING_KEY, post);
        return "Post published to the topic";
    }
}
