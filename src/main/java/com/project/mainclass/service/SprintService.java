package com.project.mainclass.service;

import com.project.mainclass.Model.SprintDetails;
import com.project.mainclass.repository.SprintRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

  @Autowired
  private SprintRepository sprintRepository;

  public Object get(SprintDetails sprintDetails) {
    return sprintRepository.save(sprintDetails);
  }

  public Optional<SprintDetails> get(String id) {
    return sprintRepository.findById(id);
  }

  public List<SprintDetails> getAll() {
    return sprintRepository.findAll();
  }

  public void get1(String id) {
    sprintRepository.deleteById(id);
  }
}
