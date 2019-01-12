package com.ohtacaesar.misc.spring_boot_sandbox;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Tag {

  @Id
  @GeneratedValue
  private int id;

  @Column(length = 20, nullable = false)
  private String name;
}
