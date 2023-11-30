package gsc.projects.stockservice.kafka.consumer;


import gsc.projects.basedomains.events.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "topic_orderzzz")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event received at stock service -> %s", orderEvent.toString()));
    }
}
