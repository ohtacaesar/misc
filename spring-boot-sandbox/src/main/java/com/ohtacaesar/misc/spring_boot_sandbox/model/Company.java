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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

  @OneToOne(cascade = CascadeType.PERSIST)
  private CompanyHistory history = new CompanyHistory();

  @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
  private List<CompanyHistory> historyList;

  @Id
  @GeneratedValue
  private int id;

  @PrePersist
  @PreUpdate
  private void preUpdate() {
    this.history.setCompany(this);
  }

}
