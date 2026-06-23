package com.application.auth.service;

import com.application.auth.dto.UserDto;
import com.application.auth.entity.User;
import com.application.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

        @Autowired
        UserRepository userRepo;

        public String login(UserDto userDto){

            User userEntity=userRepo
                    .findByName(userDto.getUsername())
                    .orElseThrow(()-> new RuntimeException("user not found"));


            if(userEntity.getPassword().equals(userDto.getPassword())){
                return "logging sucsses";
            }
            throw new RuntimeException("invalid password");
        }
    }

