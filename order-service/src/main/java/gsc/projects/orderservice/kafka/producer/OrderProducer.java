package gsc.projects.orderservice.kafka.producer;


import gsc.projects.basedomains.events.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent (OrderEvent orderEvent){
        LOGGER.info(String.format("Order event sent to order topic -> %s", orderEvent.toString()));

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, "topic_orderzzz")
                .build();

        kafkaTemplate.send(message);
    }
}
