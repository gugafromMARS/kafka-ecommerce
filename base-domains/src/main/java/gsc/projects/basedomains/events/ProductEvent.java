package gsc.projects.basedomains.events;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

    private String name;

    private int quantity;

    @Override
    public String toString() {
        return "ProductEvent{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
