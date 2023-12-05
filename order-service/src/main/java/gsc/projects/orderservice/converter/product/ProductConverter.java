package gsc.projects.orderservice.converter.product;


import gsc.projects.basedomains.events.ProductEvent;
import gsc.projects.basedomains.events.ShippingProduct;
import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductDto;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.model.product.ProductOrder;
import gsc.projects.orderservice.repository.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    private  ProductRepository productRepository;

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
        product.setQuantity(productOrder.getQuantity());
        return product;
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
