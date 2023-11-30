package gsc.projects.orderservice.service.product;

import gsc.projects.orderservice.dto.order.OrderDto;
import gsc.projects.orderservice.dto.product.ProductCreateDto;
import gsc.projects.orderservice.dto.product.ProductDto;
import gsc.projects.orderservice.dto.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductCreateDto productCreateDto);

    ProductDto getByName(String productName);

    void deleteById(Long productId);

    ProductDto updateById(Long productId, ProductUpdateDto productUpdateDto);

    List<OrderDto> getAllOrders(String productName);
}
