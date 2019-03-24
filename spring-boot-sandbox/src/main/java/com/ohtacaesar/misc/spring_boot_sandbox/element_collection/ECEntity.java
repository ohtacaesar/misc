package com.ohtacaesar.misc.spring_boot_sandbox.element_collection;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
public class ECEntity {

  @Id
  @GeneratedValue
  private int id;

  @Fetch(FetchMode.SUBSELECT)
  @ElementCollection
  private List<String> a = new ArrayList<>();

  @Fetch(FetchMode.SUBSELECT)
  @ElementCollection
  private List<String> b = new ArrayList<>();

}
