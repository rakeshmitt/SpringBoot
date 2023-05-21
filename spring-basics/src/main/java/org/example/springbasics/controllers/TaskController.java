package org.example.springbasics.controllers;

import org.example.springbasics.entities.Task;
import org.example.springbasics.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task){
       return taskService.createTask(task.getTitle(), task.getDescription(), task.getTaskDate());
    }

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable("id") Integer id){


      return taskService.getTaskById(id);
    }

    @DeleteMapping("/task/{id}")
    public Task deleteTask(@PathVariable("id") Integer id){
        return taskService.deleteTask(id);
    }

    @PatchMapping("/task/{id}")
    public Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        return taskService.updateTask(id, task.getTitle(), task.getDescription(), task.getTaskDate());
    }


}
