package org.example.springtaskmgrv2.repositories;

import org.example.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TasksRepositoryTests {

    @Autowired
    private TasksRepository tasksRepository;

    @Test
    public void testCreateTask(){
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle("Test Task");
        taskEntity.setDescription("test task desc");
        taskEntity.setCompleted(false);
        var savedTask = tasksRepository.save(taskEntity);
        assertNotNull(savedTask);
    }
}
