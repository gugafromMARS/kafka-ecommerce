package gsc.projects.stockservice.converter;


import gsc.projects.basedomains.events.ProductEvent;
import gsc.projects.stockservice.dto.ProductDto;
import gsc.projects.stockservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {


    public Product fromProductEvent(ProductEvent productEvent){
        return Product.builder()
                .withName(productEvent.getName())
                .withQuantity(productEvent.getQuantity())
                .build();
    }

    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}
