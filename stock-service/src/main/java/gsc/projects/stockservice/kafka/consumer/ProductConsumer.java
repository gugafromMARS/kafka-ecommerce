package gsc.projects.stockservice.kafka.consumer;

import gsc.projects.basedomains.events.OrderEvent;
import gsc.projects.basedomains.events.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "topic_productzzz")
    public void consume(ProductEvent productEvent){
        LOGGER.info(String.format("Order event received at stock service -> %s", productEvent.toString()));
    }
}
