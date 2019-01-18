package com.ohtacaesar.misc.spring_boot_sandbox;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ohtacaesar.misc.spring_boot_sandbox.Views.Simple;
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
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Tag {

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
  private List<ItemTag> itemTagList = new ArrayList<>();

  @Id
  @GeneratedValue
  @JsonView(Simple.class)
  private int id;

  @Column(length = 20, nullable = false)
  @JsonView(Simple.class)
  private String name;
}
