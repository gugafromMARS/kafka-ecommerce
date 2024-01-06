package gsc.projects.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.basedomains.dto.UserDto;
import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserUpdateDto;
import gsc.projects.userservice.model.User;
import gsc.projects.userservice.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    UserCreatedDto userCreatedDto;
    User user;
    UserDto userDto;

    UserUpdateDto userUpdateDto;
    @BeforeEach
    void setup(){
        userRepository.deleteAll();

        userCreatedDto = new UserCreatedDto();
        userCreatedDto.setName("test");
        userCreatedDto.setEmail("test@email.com");
        userCreatedDto.setAddress("Test street");

        user = new User();
        user.setName(userCreatedDto.getName());
        user.setEmail(userCreatedDto.getEmail());
        user.setAddress(userCreatedDto.getAddress());

        userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
    }


    void persistUser(){
        userRepository.save(user);
    }

    void updateUser(){
        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setAddress("new street");
        userDto.setAddress(userUpdateDto.getAddress());
    }

    @Nested
    @Tag("User service integration tests")
    public class UserIntegrationTests{

        @Test
        @DisplayName("Create a non user and return 201")
        public void createANonUser200() throws Exception {

            ResultActions response = mockMvc.perform(post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreatedDto)));



            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.email", is(userDto.getEmail())));
        }

        @Test
        @DisplayName("Try to create an exists user and return 400")
        public void tryCreateExistsUser400() throws Exception {

            persistUser();

            ResultActions response = mockMvc.perform(post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreatedDto)));


            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }


        @Test
        @DisplayName("Get an exists user and return 200")
        public void GetAnExistsUser200() throws Exception {

            persistUser();

            ResultActions response = mockMvc.perform(get("/api/user/{userEmail}", userCreatedDto.getEmail()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to get an user with invalid email and return 404")
        public void tryToGetAUserWithInvalidEmail404() throws Exception {

            ResultActions response = mockMvc.perform(get("/api/user/{userEmail}", "invalid@email.com"));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete an exists user by id and return 200")
        public void deleteAnExistsUser200() throws Exception {

            persistUser();

            ResultActions response = mockMvc.perform(delete("/api/user/{userId}", user.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Update address to an exists user and return 200")
        public void updateAddressToExistsUser200() throws Exception {

            persistUser();
            updateUser();

            ResultActions response = mockMvc.perform(put("/api/user/{userId}", user.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userUpdateDto)));


            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.address", is(userDto.getAddress())));
        }
    }

}