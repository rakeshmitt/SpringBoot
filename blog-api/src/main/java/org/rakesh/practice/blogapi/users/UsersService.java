package org.rakesh.practice.blogapi.users;

import org.modelmapper.ModelMapper;
import org.rakesh.practice.blogapi.users.dtos.CreateUserDTO;
import org.rakesh.practice.blogapi.users.dtos.LoginUserDTO;
import org.rakesh.practice.blogapi.users.dtos.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    /*

     */
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        //TODO: encrypt password encryption
        //TODO: email validation
        //TODO: check if user already exists
        UserEntity newUser = modelMapper.map(createUserDTO, UserEntity.class);
        var savedUser = usersRepository.save(newUser);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){

        UserEntity userEntity = usersRepository.findByUsername(loginUserDTO.getUsername());


        if(userEntity == null) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }

        //TODO: implement password hashing
        if(!userEntity.getPassword().equals(loginUserDTO.getPassword())) {
            throw new PasswordMismatchException(loginUserDTO.getUsername());
        }

        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
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
