package gsc.projects.basedomains.events;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderEvent {

    private List<ProductEvent> products;

    @Override
    public String toString() {
        return "OrderEvent{" +
                "products=" + products +
                '}';
    }
}
