package gsc.projects.orderservice.service.product;


import gsc.projects.orderservice.converter.product.ProductConverter;
import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductDto;
import gsc.projects.orderservice.dto.product.ProductUpdateDto;
import gsc.projects.orderservice.model.product.Product;
import gsc.projects.orderservice.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Product product = productRepository.findByName(productCreateDto.getName().toUpperCase());
        if(product != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
        }
        Product newProduct = productConverter.fromCreateDto(productCreateDto);
        productRepository.save(newProduct);
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
        productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }


    @Override
    public ProductDto updateById(Long productId, ProductUpdateDto productUpdateDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        existingProduct.setPrice(productUpdateDto.getPrice());
        productRepository.save(existingProduct);
        return productConverter.toDto(existingProduct);
    }
}
