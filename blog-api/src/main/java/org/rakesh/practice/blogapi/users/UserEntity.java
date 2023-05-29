package org.rakesh.practice.blogapi.users;

import org.rakesh.practice.blogapi.commons.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String username;
    String password; //TODO: Using HASH
    String bio;
    String image;

}
