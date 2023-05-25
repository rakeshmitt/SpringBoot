package org.example.springtaskmgrv2.controllers;

import org.apache.coyote.Response;
import org.example.springtaskmgrv2.dtos.ErrorResponse;
import org.example.springtaskmgrv2.entities.TaskEntity;
import org.example.springtaskmgrv2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;


    /**
     * for optional params we can either use required as false or define the param as optional
     * @param title
     * @param completed
     * @return
     */
    @GetMapping(value="/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks(@RequestParam(required = false) String title,
                                                        @RequestParam Optional<Boolean> completed){

        List<TaskEntity> tasks = null;
        if(title != null && !title.trim().equals(""))
            tasks = taskService.getTaskByTitle(title);
        else if(completed.isPresent())
            tasks = taskService.getTaskByCompleted((completed.get()));
        else
            tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);

    }

    @GetMapping(value="/tasks/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value="/tasks")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task){
        TaskEntity newTask = taskService.createTask(task.getTitle(), task.getDescription(), task.getDueDate());
        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    @PatchMapping(value="/tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer taskId, @RequestBody TaskEntity task) {
        TaskEntity updatedTask = taskService.updateTask(taskId, task.getTitle(), task.getDescription(), task.getDueDate() );
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping(value="/tasks/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") Integer taskId){
        return ResponseEntity.accepted().body(taskService.deleteTask(taskId));
    }

    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskService.TaskNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }



}
