package com.project.mainclass.controller;

import com.project.mainclass.Model.SprintDetails;
import com.project.mainclass.service.SprintService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sprint")
public class SprintController {

  @Autowired
  private SprintService sprintService;

  @PostMapping
  public Object createSprint(@RequestBody SprintDetails sprintDetails) {
    return sprintService.get(sprintDetails);
  }

  @GetMapping(value = "/id")
  public Optional<SprintDetails> getById(@RequestParam("id") String id) {
    return sprintService.get(id);
  }

  @GetMapping
  public List<SprintDetails> get() {
    return sprintService.getAll();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") String id) {
    sprintService.get1(id);
  }
}
