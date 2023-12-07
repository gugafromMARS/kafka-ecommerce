package gsc.projects.shippingservice.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic shippingTopic(){
        return TopicBuilder
                .name("shipping_order")
                .build();
    }
}
