package com.ohtacaesar.misc.spring_boot_sandbox.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Company {

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private List<CompanyHistory> historyList;

  @OneToOne(cascade = CascadeType.ALL)
  private CompanyHistory history = new CompanyHistory();

  @Id
  @GeneratedValue
  private int id;

}
