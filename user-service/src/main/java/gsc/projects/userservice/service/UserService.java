package gsc.projects.userservice.service;

import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserUpdateDto;

public interface UserService {
    UserDto createUser(UserCreatedDto userCreatedDto);

    UserDto getUserByEmail(String userEmail);

    void deleteById(Long userId);

    UserDto updateById(Long userId, UserUpdateDto userUpdateDto);
}
