package org.rakesh.practice.blogapi.users;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.rakesh.practice.blogapi.users.dtos.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsersServiceTest {

    @Autowired private UsersRepository usersRepository;

    /*
        the application context is not loaded because of @DataJpaTest annotation. When change it to @SpringBootTest it works.
        @DataJpaTest only loads the JPA part of a Spring Boot application.
    */
    private UsersService createUserService() {
        return new UsersService(
                usersRepository,
                new ModelMapper()
        );
    }

    @Test
    public void testCreateUser(){
        var newUserDTO = new CreateUserDTO();
        newUserDTO.setEmail("test1@test.com");
        newUserDTO.setUsername("test111");
        newUserDTO.setPassword("abcd");

        var savedUser = createUserService().createUser(newUserDTO);
        assertNotNull(savedUser);
    }
}
