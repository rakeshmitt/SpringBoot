package org.example.springtaskmgrv2.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name="notes")
public class NoteEntity extends BaseEntity {


    @Column(name="body", nullable = false, length=500)
    String body;

    @ManyToOne
    TaskEntity task;
}
