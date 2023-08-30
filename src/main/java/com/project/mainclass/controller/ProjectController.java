package com.project.mainclass.controller;

import com.project.mainclass.Model.ProjectDetails;
import com.project.mainclass.service.ProjectService;
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
@RequestMapping(value = "/Project")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @PostMapping
  public String createDetails(@RequestBody ProjectDetails projectDetails) {
    Optional<ProjectDetails> pDetails = projectService.Create(projectDetails);
    if (pDetails.isPresent()) {
      return "value has been inserted";
    }
    {
      return "value has not been inserted";
    }
  }

  @GetMapping
  public List<ProjectDetails> getAllProject() {
    return projectService.getAll();
  }

  @GetMapping(value = "id")
  public Optional<ProjectDetails> getById(@RequestParam("id") String id) {
    return projectService.get(id);
  }

  @GetMapping(value = "name")
  public Optional<ProjectDetails> getByName(@RequestParam("name") String name) {
    return projectService.get1(name);
  }

  @DeleteMapping(value = "id")
  public void deleteById(@RequestParam("id") String id) {
    projectService.get2(id);
  }
}
