package com.project.mainclass.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@Data
public class SprintDetails {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String sprintId;

  private String assigendBy;
  private String assigendTo;
  private Date dewDate;

  @ManyToOne
  @JoinColumn(name = "projec_details_id", nullable = false)
  private ProjectDetails projectDetails;

  @OneToMany(mappedBy = "sprintDetails")
  @JsonIgnore
  private List<TaskDetails> taskDetails;
}
