package gsc.projects.userservice.service;

import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.userservice.converter.UserConverter;
import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserUpdateDto;
import gsc.projects.userservice.model.User;
import gsc.projects.userservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class UserServiceImpTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserConverter userConverter;
    @InjectMocks
    UserServiceImp userServiceImp;

    User user;
    UserCreatedDto userCreatedDto;
    UserDto userDto;
    UserUpdateDto userUpdateDto;
    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userCreatedDto = new UserCreatedDto();
        userCreatedDto.setName("test");
        userCreatedDto.setEmail("test@email.com");

        user = new User();
        user.setName(userCreatedDto.getName());
        user.setEmail(userCreatedDto.getEmail());

        userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setAddress("A new street");
    }

    @Nested
    @Tag("User service unit tests")
    public class UserServiceUnitTests {


        @Test
        @DisplayName("Create an user an return user dto")
        public void createAnUserReturnUserDto(){

            given(userRepository.findByEmail(user.getEmail())).willReturn(null);

            when(userRepository.save(user)).thenReturn(user);

            assertEquals(userDto.getId(), user.getId());
        }

        @Test
        @DisplayName("Try to create an user with same email")
        public void tryToCreateUserWithSameEmail(){

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findByEmail(user.getEmail())).willReturn(user);

            when(userRepository.findByEmail(userCreatedDto.getEmail())).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                userServiceImp.createUser(userCreatedDto);
            });
        }

        @Test
        @DisplayName("Delete an exists user")
        public void deleteExistsUser(){


            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findByEmail(user.getEmail())).willReturn(user);

            userRepository.delete(user);

            when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        }

        @Test
        @DisplayName("Update an user")
        public void updateAnUser(){

            userDto.setAddress(userUpdateDto.getAddress());

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));

            when(userServiceImp.updateById(user.getId(), userUpdateDto)).thenReturn(userDto);

            assertEquals(userDto.getAddress(), user.getAddress());
        }

    }


}