package org.rakesh.practice.blogapi.commons;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @CreatedDate()
    @Column(name="created_date", updatable = false)
    public Date createdAt;

    @LastModifiedDate
    @Column(name="last_modified_date", updatable = false)
    public Date modifiedDate;
}
