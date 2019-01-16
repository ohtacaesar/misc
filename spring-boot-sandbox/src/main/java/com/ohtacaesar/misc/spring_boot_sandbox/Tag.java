package com.ohtacaesar.misc.spring_boot_sandbox;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Setter
@Getter
public class Tag {

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<ItemTag> itemTagList = new ArrayList<>();

  @Id
  @GeneratedValue
  private int id;

  @Column(length = 20, nullable = false)
  private String name;
}
