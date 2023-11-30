package gsc.projects.basedomains.events;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private List<ProductEvent> products;

    @Override
    public String toString() {
        return "OrderEvent{" +
                "products=" + products +
                '}';
    }
}
