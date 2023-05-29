package org.rakesh.practice.blogapi.articles;

import org.apache.catalina.User;
import org.rakesh.practice.blogapi.commons.BaseEntity;
import org.rakesh.practice.blogapi.users.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name="articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique=true, nullable=false, length=150)
    String slug;
    @Column(nullable = false, length=200)
    String title;
    String subtitle;
    @Column(nullable = false, length = 8000)
    String body;
    String[] tagList; //TODO
    @ManyToOne
    UserEntity author;

    @ManyToMany
    @JoinTable(name = "article_likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    List<UserEntity> likedBy;

}
