package org.example.springbasics.controllers;

import org.apache.coyote.Response;
import org.example.springbasics.dtos.ErrorResponse;
import org.example.springbasics.entities.Task;
import org.example.springbasics.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Task>> getAllTasks(){

        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task newTask = taskService.createTask(task.getTitle(), task.getDescription(), task.getTaskDate());
       return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id){
      return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id){
        Task deletedTask = taskService.deleteTask(id);
        return ResponseEntity.accepted().body(deletedTask);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        Task updatedTask = taskService.updateTask(id, task.getTitle(), task.getDescription(), task.getTaskDate());
        return ResponseEntity.accepted().body(updatedTask);
    }

    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskService.TaskNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
