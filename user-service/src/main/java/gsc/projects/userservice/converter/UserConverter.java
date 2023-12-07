package gsc.projects.userservice.converter;


import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }

    public User fromCreateDto(UserCreatedDto userCreatedDto){
        return User.builder()
                .withName(userCreatedDto.getName())
                .withAge(userCreatedDto.getAge())
                .withEmail(userCreatedDto.getEmail())
                .withAddress(userCreatedDto.getAddress())
                .build();
    }
}
