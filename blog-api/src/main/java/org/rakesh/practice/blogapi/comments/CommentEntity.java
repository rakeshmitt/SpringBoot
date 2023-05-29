package org.rakesh.practice.blogapi.comments;

import org.rakesh.practice.blogapi.articles.ArticleEntity;
import org.rakesh.practice.blogapi.commons.BaseEntity;
import org.rakesh.practice.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable=false, length=100)
    String title;
    @Column(length=1000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;
}
