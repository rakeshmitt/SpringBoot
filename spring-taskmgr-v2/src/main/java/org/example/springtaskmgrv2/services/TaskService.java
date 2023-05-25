package org.example.springtaskmgrv2.services;


import org.example.springtaskmgrv2.entities.TaskEntity;
import org.example.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TasksRepository tasksRepository;

    public static class TaskNotFoundException extends IllegalArgumentException {

        public TaskNotFoundException(Integer id){
            super("task with id " + id + " not found.");
        }

        public TaskNotFoundException(String title){
            super("task with title " + title + " not found.");
        }

        public TaskNotFoundException(Boolean completed){
            super("task with completed status as " + completed + " not found.");
        }
    }

    public List<TaskEntity> getAllTasks(){
        return tasksRepository.findAll();
    }

    public TaskEntity getTaskById(Integer taskId){
        TaskEntity task = tasksRepository.findById(taskId).get();
        if(task==null) throw new TaskNotFoundException(taskId);
        return task;
    }

    public TaskEntity createTask(String title, String description,Date dueDate){

        TaskEntity newTask = new TaskEntity();

        newTask.setDueDate(dueDate);
        newTask.setDescription(description);
        newTask.setTitle(title);
        return tasksRepository.save(newTask);
    }

    public TaskEntity updateTask(Integer taskId, String title, String description, Date dueDate){

        TaskEntity task = getTaskById(taskId);
        if(dueDate != null) task.setDueDate(dueDate);
        if(description != null) task.setDescription(description);
        if(title != null) task.setTitle(title);
        return tasksRepository.save(task);
    }

    public TaskEntity deleteTask(Integer taskId){
        TaskEntity task = getTaskById(taskId);
        tasksRepository.deleteById(taskId);
        return task;
    }

    public List<TaskEntity> getTaskByTitle(String title){
        List<TaskEntity> task = tasksRepository.findAllByTitle(title);
        if(task==null) throw new TaskNotFoundException(title);
        return task;
    }

    public List<TaskEntity> getTaskByCompleted(Boolean completed){
        List<TaskEntity> task = tasksRepository.findAllByCompleted(completed);
        if(task==null) throw new TaskNotFoundException(completed);
        return task;
    }
}
