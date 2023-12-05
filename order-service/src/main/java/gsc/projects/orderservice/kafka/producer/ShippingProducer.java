package gsc.projects.orderservice.kafka.producer;


import gsc.projects.basedomains.events.ShippingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ShippingProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingProducer.class);
    private KafkaTemplate<String, ShippingOrder> kafkaTemplate;

    public ShippingProducer(KafkaTemplate<String, ShippingOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendShippingOrder(ShippingOrder shippingOrder){
        LOGGER.info(String.format("Shipping order sent to shipping service -> %s", shippingOrder.toString()));

        Message<ShippingOrder> message = MessageBuilder
                .withPayload(shippingOrder)
                .setHeader(KafkaHeaders.TOPIC, "topic_shipping")
                .build();

        kafkaTemplate.send(message);
    }
}
