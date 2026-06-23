package com.application.auth.service;

import com.application.auth.dto.AuthResponse;
import com.application.auth.dto.RegisterRequest;
import com.application.auth.dto.Role;
import com.application.auth.dto.UserDto;
import com.application.auth.entity.User;
import com.application.auth.exeption.AuthException;
import com.application.auth.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
 private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

       if(userRepository.existsByEmail(registerRequest.getEmail()))
        {
            throw new AuthException("Email alredy exist in the database", HttpStatus.CONFLICT);

        }


       //============================================================================================================
//        User user=new User();
//       user.setName(registerRequest.getName());
//       user.setEmail(registerRequest.getEmail());
//       user.setPassword(registerRequest.getPassword());
//       user.setRole(Role.USER);

        String password= passwordEncoder.encode(registerRequest.getPassword());


      User user=  User.builder().email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(password)
                .role(Role.USER)
                .build();

       userRepository.save(user);

//========================================================================================
//       AuthResponse authResponse=new AuthResponse();
//       authResponse.setEmail(user.getEmail());
//       authResponse.setRole(Role.USER.name());
//       authResponse.setMassage("User Succesfully Registerd");
//
//       return authResponse;


        AuthResponse authResponse=AuthResponse.builder()
                .email(user.getEmail())
                .role(Role.USER.name())
                .massage("User Succesfully Registerd")
                .build();

        return authResponse;




    }

    public String login(UserDto userDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        return jwtService.generateToken(userDto.getUsername());
    }
}
