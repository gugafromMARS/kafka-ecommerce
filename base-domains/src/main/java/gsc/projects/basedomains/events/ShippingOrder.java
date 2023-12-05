package gsc.projects.basedomains.events;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingOrder {

    private String userName;
    private String userAddress;
    List<ShippingProduct> shippingProducts;
    private double totalPrice;

    @Override
    public String toString() {
        return "ShippingOrder{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", shippingProducts=" + shippingProducts +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
