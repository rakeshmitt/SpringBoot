package org.example.springtaskmgrv2.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name="tasks")
//@Table(indexes = @Index(columnList="title"))
public class TaskEntity extends BaseEntity {

    @Column(name="title", nullable = false, length=150)
    String title;

    @Column(name="description", nullable = true, length=1000)
    String description;

    @Column(name="completed", columnDefinition="BOOLEAN DEFAULT FALSE")
    Boolean completed=false;

    @Column(name="due_date", nullable = true, columnDefinition="DATE" )
    Date dueDate;

    /**
     * keep the below codebase only if we want to fetch notes while fetching tasks
     * otherwise we can fetch notes separately.
     *
     * FetchType can be EAGER or LAZY. Not all DB supports LAZY loading.
     */
   /* @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    List<NoteEntity> notes;

    */
}
