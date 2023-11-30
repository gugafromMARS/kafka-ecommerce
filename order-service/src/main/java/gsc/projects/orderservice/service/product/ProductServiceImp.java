package gsc.projects.orderservice.service.product;


import gsc.projects.orderservice.converter.order.OrderConverter;
import gsc.projects.orderservice.converter.product.ProductConverter;
import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductDto;
import gsc.projects.orderservice.dto.product.ProductUpdateDto;
import gsc.projects.orderservice.kafka.producer.ProductProducer;
import gsc.projects.orderservice.model.order.Order;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;
    private final OrderConverter orderConverter;
    private final ProductProducer productProducer;

    @Override
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Product product = productRepository.findByName(productCreateDto.getName().toUpperCase());
        if(product != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
        }
        Product newProduct = productConverter.fromCreateDto(productCreateDto);
        productRepository.save(newProduct);
        productProducer.sendProductEvent(productConverter.fromProduct(newProduct));
        return productConverter.toDto(newProduct);
    }


    @Override
    public ProductDto getByName(String productName) {
        Product existingProduct = productRepository.findByName(productName);
        if(existingProduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return productConverter.toDto(existingProduct);
    }


    @Override
    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(product);
    }


    @Override
    public ProductDto updateById(Long productId, ProductUpdateDto productUpdateDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        existingProduct.setPrice(productUpdateDto.getPrice());
        productRepository.save(existingProduct);
        return productConverter.toDto(existingProduct);
    }

    @Override
    public List<OrderDto> getAllOrders(String productName) {
        Product existingProduct = productRepository.findByName(productName.toUpperCase());
        if(existingProduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return existingProduct.getOrders().stream()
                .map(order -> orderConverter.toDto(order))
                .toList();
    }
}
