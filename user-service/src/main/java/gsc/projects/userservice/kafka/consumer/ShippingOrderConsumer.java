package gsc.projects.userservice.kafka.consumer;


import gsc.projects.basedomains.events.ShippingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingOrderConsumer {


    public static final Logger LOGGER = LoggerFactory.getLogger(ShippingOrderConsumer.class);

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "shipping_order")
    public void consume(ShippingOrder shippingOrder){
        LOGGER.info(String.format("Shipping order received in user service -> %s", shippingOrder.toString()));
    }
}
