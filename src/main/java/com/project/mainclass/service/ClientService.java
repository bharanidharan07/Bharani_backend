package com.project.mainclass.service;

import com.project.mainclass.Model.ClientDetails;
import com.project.mainclass.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  // To create clientDetails
  public Optional<ClientDetails> Create(ClientDetails clientDetails) {
    ClientDetails cDetails = clientRepository.save(clientDetails);
    return Optional.of(cDetails);
  }

  public Optional<ClientDetails> getValue(String id) {
    return clientRepository.findById(id);
  }

  public List<ClientDetails> getAll() {
    return clientRepository.findAll();
  }

  public List<ClientDetails> getByName(String name) {
    return clientRepository.findByName(name);
  }

  public void delete(String id) {
    clientRepository.deleteById(id);
  }
}
