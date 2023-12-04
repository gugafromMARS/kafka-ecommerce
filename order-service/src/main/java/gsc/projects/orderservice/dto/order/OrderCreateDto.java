package gsc.projects.orderservice.dto.order;


import gsc.projects.orderservice.model.product.ProductOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDto {

    private List<ProductOrder> productOrders;
    private String userEmail;
}
