package com.crio.stayEase.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.crio.stayEase.dto.UserDto;
import com.crio.stayEase.exchanges.LoginUserRequest;
import com.crio.stayEase.exchanges.LoginUserResponse;
import com.crio.stayEase.exchanges.RegisterUserRequest;
import com.crio.stayEase.exchanges.RegisterUserResponse;
import com.crio.stayEase.services.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterUser() throws Exception {
        String requestBody = "{\"firstName\": \"Prajwal\",\"lastName\": \"P G\",\"email\": \"pgprajwal@gmail.com\",\"password\": \"password\",\"role\": \"CUSTOMER\"}";

        UserDto user = new UserDto();
        user.setId(1);
        user.setFirstName("Prajwal");
        user.setLastName("P G");
        user.setEmail("pgprajwal@gmail.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user.setBookings(new HashSet<>());

        String jwtToken = "INPUT_YOUR_256_BIT_SECRET_KEY";
        RegisterUserResponse registerUserResponse = new RegisterUserResponse(user, jwtToken);

        when(userService.registerUser(any(RegisterUserRequest.class))).thenReturn(registerUserResponse);

        mockMvc.perform(post("/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userDto.id").value(1))
            .andExpect(jsonPath("$.userDto.firstName").value("Prajwal"))
            .andExpect(jsonPath("$.userDto.lastName").value("P G"))
            .andExpect(jsonPath("$.userDto.email").value("pgprajwal@gmail.com"))
            .andExpect(jsonPath("$.userDto.password").value("password"))
            .andExpect(jsonPath("$.userDto.role").value("CUSTOMER"));

        verify(userService, times(1)).registerUser(any(RegisterUserRequest.class));
    }

    @Test
    public void testLoginUser() throws Exception {
        String requestBody = "{\"email\": \"pgprajwal@gmail.com\",\"password\": \"password\"}";
        String jwtToken = "INPUT_YOUR_256_BIT_SECRET_KEY";
        LoginUserResponse loginUserResponse = new LoginUserResponse(jwtToken);

        when(userService.loginUser(any(LoginUserRequest.class))).thenReturn(loginUserResponse);

        mockMvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.jwtToken").value(loginUserResponse.getJwtToken()));

        verify(userService, times(1)).loginUser(any(LoginUserRequest.class));
    }
}
