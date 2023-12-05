package gsc.projects.orderservice.dto.product;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private int quantity;
}
