package gsc.projects.orderservice.kafka.producer;


import gsc.projects.basedomains.events.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProducer.class);

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public ProductProducer(KafkaTemplate<String, ProductEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductEvent(ProductEvent productEvent){
        LOGGER.info(String.format("Product event sent to product topic -> %s", productEvent.toString()));

        Message<ProductEvent> message = MessageBuilder
                .withPayload(productEvent)
                .setHeader(KafkaHeaders.TOPIC, "topic_productz")
                .build();

        kafkaTemplate.send(message);
    }
}
