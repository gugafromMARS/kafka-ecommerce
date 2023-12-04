package gsc.projects.userservice.service;

import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserDto;
import gsc.projects.userservice.dto.UserUpdateDto;

public interface UserService {
    UserDto createUser(UserCreatedDto userCreatedDto);

    UserDto getUserById(Long userId);

    void deleteById(Long userId);

    UserDto updateById(Long userId, UserUpdateDto userUpdateDto);
}
