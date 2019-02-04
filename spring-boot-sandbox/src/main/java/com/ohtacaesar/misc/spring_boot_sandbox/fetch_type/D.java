package com.ohtacaesar.misc.spring_boot_sandbox.fetch_type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class D {

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  private A a;
}
