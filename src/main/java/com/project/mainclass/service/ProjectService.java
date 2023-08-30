package com.project.mainclass.service;

import com.project.mainclass.Model.ProjectDetails;
import com.project.mainclass.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository ProjectRepository;

  public Optional<ProjectDetails> Create(ProjectDetails projectDetails) {
    ProjectDetails pDetails = ProjectRepository.save(projectDetails);
    return Optional.of(pDetails);
  }

  public List<ProjectDetails> getAll() {
    return ProjectRepository.findAll();
  }

  public Optional<ProjectDetails> get(String id) {
    return ProjectRepository.findById(id);
  }

  public Optional<ProjectDetails> get1(String name) {
    return ProjectRepository.findByName(name);
  }

  public void get2(String id) {
    ProjectRepository.deleteById(id);
  }
}
