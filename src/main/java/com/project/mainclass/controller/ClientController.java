package com.project.mainclass.controller;

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

import com.project.mainclass.Model.ClientDetails;
import com.project.mainclass.service.ClientService;

@RestController
@RequestMapping("/Client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  // Create client details
  @PostMapping
  public String createclient(@RequestBody ClientDetails clientDetails) {
    Optional<ClientDetails> client = clientService.Create(clientDetails);
    if (client.isPresent()) {
      return "value has been inserted";
    }
    {
      return "value has not been inserted";
    }
  }

  // To get clientDetails
  @GetMapping(value = "/id")
  public Optional<ClientDetails> getElementById(@RequestParam("id") String id) {
    return clientService.getValue(id);
  }

  @GetMapping
  public List<ClientDetails> getAllClientDetails() {
    return clientService.getAll();
  }

  @GetMapping(value = "/name")
  public List<ClientDetails> getAllClientDetails(
    @RequestParam("name") String name
  ) {
    return clientService.getByName(name);
  }

  //To delete Client Details
  @DeleteMapping(value = "/id")
  public void deleteClientDetails(@RequestParam("id") String id) {
    clientService.delete(id);
  }
}
