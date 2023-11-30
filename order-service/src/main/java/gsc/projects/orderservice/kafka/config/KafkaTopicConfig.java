package gsc.projects.orderservice.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder
                .name("topic_orderzzz")
                .build();
    }

    @Bean
    public NewTopic productTopic(){
        return TopicBuilder
                .name("topic_productzzz")
                .build();
    }
}
