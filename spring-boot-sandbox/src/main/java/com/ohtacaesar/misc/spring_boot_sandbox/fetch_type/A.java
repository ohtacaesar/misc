package com.ohtacaesar.misc.spring_boot_sandbox.fetch_type;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
public class A {

  @Id
  @GeneratedValue
  private int id;

  @OneToMany(mappedBy = "a", cascade = CascadeType.ALL)
  @Fetch(FetchMode.SELECT)
  private List<B> bList = new ArrayList<>();

  @OneToMany(mappedBy = "a", cascade = CascadeType.ALL)
  @Fetch(FetchMode.SUBSELECT)
  private List<C> cList = new ArrayList<>();

  @OneToMany(mappedBy = "a", cascade = CascadeType.ALL)
  @Fetch(FetchMode.JOIN)
  private List<D> dList = new ArrayList<>();

  @PrePersist
  @PreUpdate
  void prePersist() {
    for (B o : bList) {
      o.setA(this);
    }
    for (C o : cList) {
      o.setA(this);
    }
    for (D o : dList) {
      o.setA(this);
    }
  }

}
