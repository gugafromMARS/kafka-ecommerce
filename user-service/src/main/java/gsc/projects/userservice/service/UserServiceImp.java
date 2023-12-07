package gsc.projects.userservice.service;

import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.userservice.converter.UserConverter;
import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserUpdateDto;
import gsc.projects.userservice.model.User;
import gsc.projects.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {


    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserCreatedDto userCreatedDto) {
        User existingUser = userRepository.findByEmail(userCreatedDto.getEmail());
        if(existingUser != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        User newUser = userConverter.fromCreateDto(userCreatedDto);
        userRepository.save(newUser);
        return userConverter.toDto(newUser);
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {
        User existingUser = userRepository.findByEmail(userEmail);
         if(existingUser == null){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
         }
        return userConverter.toDto(existingUser);
    }

    @Override
    public void deleteById(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(existingUser);
    }

    @Override
    public UserDto updateById(Long userId, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        existingUser.setAddress(userUpdateDto.getAddress());
        userRepository.save(existingUser);
        return userConverter.toDto(existingUser);
    }
}
