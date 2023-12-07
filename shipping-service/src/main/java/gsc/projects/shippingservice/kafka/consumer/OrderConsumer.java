package gsc.projects.shippingservice.kafka.consumer;


import gsc.projects.basedomains.events.ShippingOrder;
import gsc.projects.shippingservice.kafka.producer.ShippingProducer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private final ShippingProducer shippingProducer;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "topic_shipping")
    public void consume(ShippingOrder shippingOrder){
        LOGGER.info(String.format("Shipping order received from order service -> %s", shippingOrder.toString()));

        shippingProducer.send(shippingOrder);
    }
}
