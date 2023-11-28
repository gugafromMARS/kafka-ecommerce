package gsc.projects.orderservice.converter.product;


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

    public Product fromProductOrder(ProductOrder productOrder){
        return productRepository.findByName(productOrder.getName());
    }
    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
    public Product fromCreateDto(ProductCreateDto productCreateDto){
        return Product.builder()
                .withName(productCreateDto.getName())
                .withProductType(productCreateDto.getProductType())
                .withPrice(productCreateDto.getPrice())
                .build();
    }
}
