package gsc.projects.stockservice.kafka.consumer.product;

import gsc.projects.basedomains.events.ProductEvent;
import gsc.projects.stockservice.kafka.consumer.order.OrderConsumer;
import gsc.projects.stockservice.service.StockServiceImp;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private final StockServiceImp stockServiceImp;


    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "topic_productzzz")
    public void consume(ProductEvent productEvent){
        LOGGER.info(String.format("Product event received at stock service -> %s", productEvent.toString()));
        stockServiceImp.saveProduct(productEvent);
    }
}
