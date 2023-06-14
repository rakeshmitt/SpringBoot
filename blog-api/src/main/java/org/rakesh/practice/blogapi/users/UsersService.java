package org.rakesh.practice.blogapi.users;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rakesh.practice.blogapi.security.jwt.JWTService;
import org.rakesh.practice.blogapi.users.dtos.CreateUserDTO;
import org.rakesh.practice.blogapi.users.dtos.LoginUserDTO;
import org.rakesh.practice.blogapi.users.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    /*

     */
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;


    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        //TODO: email validation
        //TODO: check if user already exists
        UserEntity newUser = modelMapper.map(createUserDTO, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = usersRepository.save(newUser);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(savedUser.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){

        UserEntity userEntity = usersRepository.findByUsername(loginUserDTO.getUsername());


        if(userEntity == null) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }

        //compare the user provided password with hashed password
        boolean passwordMatch = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());
        if(!passwordMatch) {
            throw new PasswordMismatchException(loginUserDTO.getUsername());
        }

        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(userEntity.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO getUserById(String username){
        //TODO: implement this method

        return null;
    }

    public static class UserNotFoundException extends IllegalArgumentException {

        public UserNotFoundException(Long id){
            super("User with id: " + id + " not found");
        }

        public UserNotFoundException(String username){
            super("User with username: " + username + " not found");
        }
    }

    public static class PasswordMismatchException extends IllegalArgumentException {
        public PasswordMismatchException(String username){
            super("Incorrect Password for the given username: " + username);
        }
    }


}
