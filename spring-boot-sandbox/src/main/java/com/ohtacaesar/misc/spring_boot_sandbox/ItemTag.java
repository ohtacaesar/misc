package com.ohtacaesar.misc.spring_boot_sandbox;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ITEM_ID", "TAG_ID"})
})
public class ItemTag {

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  private Item item;

  @ManyToOne
  private Tag tag;

}
