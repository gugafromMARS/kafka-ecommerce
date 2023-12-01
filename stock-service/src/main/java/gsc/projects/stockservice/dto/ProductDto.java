package gsc.projects.stockservice.dto;


import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private int quantity;
}
