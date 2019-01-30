package com.ohtacaesar.misc.spring_boot_sandbox;

import com.ohtacaesar.misc.spring_boot_sandbox.model.Company;
import com.ohtacaesar.misc.spring_boot_sandbox.model.CompanyHistory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Component;

@Component
public class Setup {

  @PersistenceUnit
  private EntityManagerFactory emf;

  @PostConstruct
  public void init() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    List<Tag> tagList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Tag tag = new Tag();
      tag.setName("タグ" + i);
      tagList.add(tag);
    }
    for (Tag tag : tagList) {
      em.persist(tag);
    }

    for (int i = 0; i < 10; i++) {
      Product product = new Product();
      product.setName("プロダクト" + i);
      product.setUrl("プロダクト" + i);
      product.getTagList().add(tagList.get(i));
      em.persist(product);
    }

    for (int i = 0; i < 10; i++) {
      Company company = new Company();
      CompanyHistory history = company.getHistory();
      history.setName("カンパニー" + i);
      history.setUrl("http://company" + i + ".com");
      em.persist(company);
    }

    Account account = new Account();
    account.setUsername("root");
    account.setPassword("root");
    em.persist(account);

    em.getTransaction().commit();
  }

}
