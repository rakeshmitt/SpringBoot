package org.rakesh.practice.blogapi.users.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String email;
    String username;
    String bio;
    String image;
    String token;
}
