package com.project.mainclass.controller;

import com.project.mainclass.Model.TaskDetails;
import com.project.mainclass.service.TaskService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping
  public Object Create(@RequestBody TaskDetails taskDetails) {
    return taskService.get(taskDetails);
  }

  @GetMapping
  public List<TaskDetails> get() {
    return taskService.getAll();
  }

  @GetMapping(value = "/id")
  public Optional<TaskDetails> getById(@RequestParam("id") String id) {
    return taskService.get1(id);
  }

  @GetMapping(value = "/name")
  public Optional<TaskDetails> getByName(@RequestParam("name") String name) {
    return taskService.getname(name);
  }

  @DeleteMapping(value = "/id")
  public void delete(String id) {
    taskService.del(id);
  }
}
