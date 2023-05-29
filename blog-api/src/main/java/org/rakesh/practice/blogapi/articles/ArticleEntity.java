package org.rakesh.practice.blogapi.articles;

import org.rakesh.practice.blogapi.commons.BaseEntity;
import org.rakesh.practice.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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

}
