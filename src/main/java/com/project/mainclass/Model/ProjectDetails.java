package com.project.mainclass.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@Data
public class ProjectDetails {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String name;
  private String details;
  private String status;

  @OneToMany(mappedBy = "projectDetails")
  @JsonIgnore
  private List<SprintDetails> sprintDetails;
}
