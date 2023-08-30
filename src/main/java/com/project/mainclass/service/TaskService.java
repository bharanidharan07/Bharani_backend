package com.project.mainclass.service;

import com.project.mainclass.Model.TaskDetails;
import com.project.mainclass.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public Object get(TaskDetails taskDetails) {
    return taskRepository.save(taskDetails);
  }

  public List<TaskDetails> getAll() {
    return taskRepository.findAll();
  }

  public Optional<TaskDetails> get1(String id) {
    return taskRepository.findById(id);
  }

  public Optional<TaskDetails> getname(String name) {
    return taskRepository.findByName(name);
  }

  public void del(String id) {
    taskRepository.deleteById(id);
  }
}
