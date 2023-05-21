package org.example.springbasics.services;

import org.example.springbasics.entities.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {

    private final List<Task> taskList;
    private AtomicInteger taskId = new AtomicInteger(1);

    public class TaskNotFoundException extends IllegalStateException
    {
        public TaskNotFoundException(Integer id){
            super("task with id " + id + " not found.");
        }
    }

    public TaskService(){
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.getAndIncrement(), "Task 1", "desc1", new Date()));
        taskList.add(new Task(taskId.getAndIncrement(), "Task 1", "desc1", new Date()));
        taskList.add(new Task(taskId.getAndIncrement(), "Task 1", "desc1", new Date()));
        taskList.add(new Task(taskId.getAndIncrement(), "Task 1", "desc1", new Date()));
    }

    public List<Task> getAllTasks(){
        return taskList;
    }

    public Task createTask(String title, String desc, Date dueDate){
        var newTask = new Task(taskId.getAndIncrement(), title, desc, dueDate);
        taskList.add(newTask);
        return newTask;
    }

    public Task getTaskById(Integer id){
        /* Task task = null;

        for(Task singleTask: taskList){
            if(singleTask.getId().equals(id)) {
                task = singleTask;
                break;
            }
        }*/
        return  taskList.stream()
                .filter(singleTask -> singleTask.getId().equals(id)).findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, String title, String desc, Date dueDate){

        var task = getTaskById(id);
        if(title != null) task.setTaskDate(dueDate);
        if(desc != null) task.setDescription(desc);
        if(dueDate != null) task.setTitle(title);
        return task;
    }

    public Task deleteTask(Integer id){
        var task = getTaskById(id);

        taskList.remove(task);
        return task;

    }
}
