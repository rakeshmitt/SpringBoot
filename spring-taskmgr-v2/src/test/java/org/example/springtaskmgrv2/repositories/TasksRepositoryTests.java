package org.example.springtaskmgrv2.repositories;

import org.example.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TasksRepositoryTests {

    @Autowired
    private TasksRepository tasksRepository;

    /**
     * Each test runs independently. Each test case creates its own inmem db to
     * execute the test.
     * If we need to execute the each test case in same db then we need to set
     * some annotation. <need to find the exact annotation></>
     */

    @Test
    public void testCreateTask(){
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle("Test Task");
        taskEntity.setDescription("test task desc");
        taskEntity.setCompleted(false);
        var savedTask = tasksRepository.save(taskEntity);
        assertNotNull(savedTask);
    }

    @Test
    public void readTaskWorks(){
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task 1");
        task1.setDescription("test task desc 1");
        task1.setCompleted(false);

        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setDescription("test task desc 2");
        task2.setCompleted(false);

        tasksRepository.save(task1);
        tasksRepository.save(task2);

        var tasks = tasksRepository.findAll();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void findByCompletedWorks(){
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task 1");
        task1.setDescription("test task desc 1");
        task1.setCompleted(false);

        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task 2");
        task2.setDescription("test task desc 2");
        task2.setCompleted(true);

        tasksRepository.save(task1);
        tasksRepository.save(task2);

        var completedTasks = tasksRepository.findAllByCompleted(true);
        var inCompleteTasks = tasksRepository.findAllByCompleted(false);

        assertEquals(1, completedTasks.size());
        assertEquals(1, inCompleteTasks.size());
    }
}
