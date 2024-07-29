package com.crio.stayEase.repositoryServices;

import java.util.Optional;

import com.crio.stayEase.dto.UserDto;

public interface UserRepositoryService {

    UserDto registerUser(String firstName, String lastName, String email, String password, String role);

    Optional<UserDto> findUserByEmail(String email);
}
