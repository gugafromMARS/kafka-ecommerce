package gsc.projects.stockservice.service;


import gsc.projects.basedomains.events.OrderEvent;
import gsc.projects.basedomains.events.ProductEvent;
import gsc.projects.stockservice.converter.ProductConverter;
import gsc.projects.stockservice.dto.ProductDto;
import gsc.projects.stockservice.model.Product;
import gsc.projects.stockservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImp {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductDto getById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return productConverter.toDto(product);
    }

    public void saveProduct(ProductEvent productEvent){
        productRepository.save(productConverter.fromProductEvent(productEvent));
    }

    public void updateProductQuantity(OrderEvent orderEvent){
        for(ProductEvent pE : orderEvent.getProducts()){
            Product product = productRepository.findByName(pE.getName());
            product.setQuantity(product.getQuantity() - pE.getQuantity());
            productRepository.save(product);
        }
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> productConverter.toDto(product))
                .toList();
    }
}
