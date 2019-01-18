package com.ohtacaesar.misc.spring_boot_sandbox;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Item {

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
  @JsonIdentityReference(alwaysAsId = true)
  private List<ItemTag> itemTagList = new ArrayList<>();

  public List<ItemTag> getItemTags() {
    return this.getItemTagList();
  }

  @Id
  @GeneratedValue
  private int id;

  @Column(length = 50, nullable = false)
  private String name;


}
