package gsc.projects.basedomains.events;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingProduct {

    private String name;
    private int quantity;
    private double price;
}
