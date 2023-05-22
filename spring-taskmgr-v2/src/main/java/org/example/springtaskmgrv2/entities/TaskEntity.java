package org.example.springtaskmgrv2.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity(name="tasks")
//@Table(indexes = @Index(columnList="title"))
public class TaskEntity extends BaseEntity {

    @Column(name="title", nullable = false, length=150)
    String title;

    @Column(name="description", nullable = true, length=1000)
    String description;

    @Column(name="completed", nullable = false, columnDefinition="boolean default false")
    Boolean completed;

    @Column(name="due_date", nullable = true)
    Date dueDate;
}
