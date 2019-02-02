package com.ohtacaesar.misc.spring_boot_sandbox.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "Index",
        attributeNodes = {@NamedAttributeNode(value = "latest")}
    ),
    @NamedEntityGraph(
        name = "Show",
        attributeNodes = {
            @NamedAttributeNode(value = "latest"),
            @NamedAttributeNode(value = "serviceList"),
        }
    )
})
public class Company {

  @OneToOne(cascade = CascadeType.PERSIST)
  private CompanyHistory latest = new CompanyHistory();

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  private List<CompanyHistory> historyList = new ArrayList<>();

  @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  private List<Service> serviceList = new ArrayList<>();

  @Id
  @GeneratedValue
  private int id;

  public void setLatest(CompanyHistory latest) {
    this.latest = latest;
    this.latest.setCompany(this);
  }

  @PrePersist
  @PreUpdate
  public void prePersist() {
    this.latest.setCompany(this);
  }

}
