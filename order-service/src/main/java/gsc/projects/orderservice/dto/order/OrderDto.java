package gsc.projects.orderservice.dto.order;


import gsc.projects.orderservice.dto.product.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {

    private Long id;
    private List<ProductDto> products;
    private double totalPrice;
    private String userEmail;

}
