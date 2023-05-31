package org.rakesh.practice.blogapi.users;

import lombok.Getter;
import lombok.Setter;
import org.rakesh.practice.blogapi.articles.ArticleEntity;
import org.rakesh.practice.blogapi.commons.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name="users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String username;
    String password; //TODO: Using HASH
    String email;
    String bio;
    String image;

    @ManyToMany(mappedBy = "likedBy")
    List<ArticleEntity> likedArticles;

    @ManyToMany
            @JoinTable(name="user_follows",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name="follower_id"))
    List<UserEntity> following;

    @ManyToMany(mappedBy = "following")
    List<UserEntity> followers;

}
