package gsc.projects.basedomains.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String address;
}
