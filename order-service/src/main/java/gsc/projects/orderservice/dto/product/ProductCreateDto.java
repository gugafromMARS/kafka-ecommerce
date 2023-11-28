package gsc.projects.orderservice.dto.product;


import gsc.projects.orderservice.model.product.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {

    private String name;
    private ProductType productType;
    private double price;

}
