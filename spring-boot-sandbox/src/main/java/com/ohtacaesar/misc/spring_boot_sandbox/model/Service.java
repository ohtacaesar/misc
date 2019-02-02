package com.ohtacaesar.misc.spring_boot_sandbox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Service {

  @ManyToOne
  private Company company;

  @Id
  @GeneratedValue
  private int id;

  private String name;

}
