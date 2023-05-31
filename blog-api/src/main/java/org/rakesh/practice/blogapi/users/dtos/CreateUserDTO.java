package org.rakesh.practice.blogapi.users.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {

    String email;
    String username;
    String password;
}
