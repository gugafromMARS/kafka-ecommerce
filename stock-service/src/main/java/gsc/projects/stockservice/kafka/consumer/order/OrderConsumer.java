package gsc.projects.stockservice.kafka.consumer.order;


import gsc.projects.basedomains.events.OrderEvent;
import gsc.projects.stockservice.service.StockServiceImp;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private final StockServiceImp stockServiceImp;


    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "topic_orderzzz")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event received at stock service -> %s", orderEvent.toString()));
        stockServiceImp.updateProductQuantity(orderEvent);
    }
}
