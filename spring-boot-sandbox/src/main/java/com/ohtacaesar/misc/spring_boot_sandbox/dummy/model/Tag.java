package com.ohtacaesar.misc.spring_boot_sandbox.dummy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ohtacaesar.misc.spring_boot_sandbox.Views.Simple;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Company;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tag {

  @ManyToMany
  @JoinTable(
      name = "TAG_COMPANY",
      joinColumns = @JoinColumn(name = "TAG_ID"),
      inverseJoinColumns = @JoinColumn(name = "COMPANY_ID")
  )
  private Set<Company> companySet = new HashSet<>();

  @Id
  @GeneratedValue
  @JsonView(Simple.class)
  private int id;

  @Column(length = 20, nullable = false, unique = true)
  @JsonView(Simple.class)
  private String name;
}
