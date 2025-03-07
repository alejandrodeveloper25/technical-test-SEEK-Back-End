package com.algonzjimz.tecnical_test.service;

import com.algonzjimz.tecnical_test.model.Task;
import com.algonzjimz.tecnical_test.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long id){
        return taskRepository.findById(id);
    }
    public List<Task> getByStatus(String status){
        return taskRepository.findByStatus(status);
    }

    public Task create(Task task){

        task.setActive(true);
        task.setStatus("start");
        task.setCreationDate(LocalDateTime.now());
        task.setDueDate(LocalDateTime.now().plusHours(24));
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return taskRepository.save(existingTask);
        });
    }

    public boolean deleteTById(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
