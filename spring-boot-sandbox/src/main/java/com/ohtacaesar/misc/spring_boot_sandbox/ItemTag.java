package com.ohtacaesar.misc.spring_boot_sandbox;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class ItemTag {

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  private Item item;
  @ManyToOne
  private Tag tag;

}
