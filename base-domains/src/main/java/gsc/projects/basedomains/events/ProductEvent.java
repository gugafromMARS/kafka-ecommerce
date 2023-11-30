package gsc.projects.basedomains.events;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductEvent {

    private String name;

    // tenho de meter quantity no product e nao esquecer de fazer um metodo para o converter ...
    private int quantity;

    @Override
    public String toString() {
        return "ProductEvent{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
