package com.ohtacaesar.misc.spring_boot_sandbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemTag {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  private Item item;
  @ManyToOne
  private Tag tag;

}
