package com.crio.stayEase.repositoryServices;

import com.crio.stayEase.dto.UserDto;
import com.crio.stayEase.entities.enums.Role;

public class UserRepositoryServiceImpl implements UserRepositoryService {

    @Override
    public UserDto registerUser(String firstName, String lastName, String email, String password, Role role) {
        
        throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    }

    @Override
    public UserDto findUserByEmail(String email) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
    }
    
}
