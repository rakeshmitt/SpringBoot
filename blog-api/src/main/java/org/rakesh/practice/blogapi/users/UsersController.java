package org.rakesh.practice.blogapi.users;

import org.rakesh.practice.blogapi.users.dtos.CreateUserDTO;
import org.rakesh.practice.blogapi.users.dtos.LoginUserDTO;
import org.rakesh.practice.blogapi.users.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {


    final UsersService usersService;


    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO){
        UserResponseDTO newUser = usersService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + newUser.getId()))
                .body(newUser);

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO){
        UserResponseDTO loggedInUser = usersService.loginUser(loginUserDTO);
        return ResponseEntity.ok(loggedInUser);

    }

    @ExceptionHandler(UsersService.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UsersService.UserNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsersService.PasswordMismatchException.class)
    public ResponseEntity<String> handlePasswordMismatchException(UsersService.PasswordMismatchException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
