package com.crio.stayEase.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.crio.stayEase.dto.UserDto;
import com.crio.stayEase.entities.User;

@Component
public class Mapper implements ApplicationContextAware{
    
    private static ModelMapper modelMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        modelMapper = applicationContext.getBean(ModelMapper.class);
    }

    public static User mapToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
