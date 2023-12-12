package gsc.projects.orderservice.converter.product;


import gsc.projects.basedomains.events.ProductEvent;
import gsc.projects.basedomains.events.ShippingProduct;
import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductDto;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.model.product.ProductOrder;
import gsc.projects.orderservice.repository.product.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    private  ProductRepository productRepository;

    private  List<Integer> quantites = new ArrayList<>();

    public ProductConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ShippingProduct fromProductToShopping(Product product){
        return ShippingProduct.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    public Product fromProductOrder(ProductOrder productOrder){
        Product product = productRepository.findByName(productOrder.getName());
        productSaveQuantity(productOrder);
        product.setQuantity(productOrder.getQuantity());
        return product;
    }

    private void productSaveQuantity(ProductOrder productOrder){
        Product product = productRepository.findByName(productOrder.getName());
        quantites.add(product.getQuantity());
    }

    public void productUpdateQuantity(Order order){
        int index = 0;
        for(Product p : order.getProducts()){
            p.setQuantity(quantites.get(index) - p.getQuantity());
            productRepository.save(p);
            index++;
        }
    }

    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
    public Product fromCreateDto(ProductCreateDto productCreateDto){
        return Product.builder()
                .withName(productCreateDto.getName())
                .withProductType(productCreateDto.getProductType())
                .withPrice(productCreateDto.getPrice())
                .withQuantity(productCreateDto.getQuantity())
                .build();
    }

    public ProductEvent fromProduct(Product product){
        return ProductEvent.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}
