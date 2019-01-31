package com.ohtacaesar.misc.spring_boot_sandbox.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedEntityGraph(attributeNodes = {@NamedAttributeNode(value = "latest")})
public class Company {

  @OneToOne(cascade = CascadeType.PERSIST)
  private CompanyHistory latest = new CompanyHistory();

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  private List<CompanyHistory> historyList;

  @Id
  @GeneratedValue
  private int id;

  public void setLatest(CompanyHistory latest) {
    this.latest = latest;
    this.latest.setCompany(this);
  }

  @PrePersist
  @PreUpdate
  public void prePersist() {
    this.latest.setCompany(this);
  }

}
