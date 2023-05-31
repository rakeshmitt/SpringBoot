package org.rakesh.practice.blogapi.users;

import org.rakesh.practice.blogapi.users.dtos.CreateUserDTO;
import org.rakesh.practice.blogapi.users.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
