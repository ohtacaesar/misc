package com.ohtacaesar.misc.spring_boot_sandbox.dummy;

import com.ohtacaesar.misc.spring_boot_sandbox.Account;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Tag;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Company;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.CompanyHistory;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Service;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Component;

@Component
public class Setup {

  @PersistenceUnit
  private EntityManagerFactory emf;

  @PostConstruct
  public void init() {
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();

    try {
      List<Tag> tagList = new ArrayList<>();
      for (int i = 1; i < 10; i++) {
        Tag tag = new Tag();
        tag.setName("タグ" + i);
        tagList.add(tag);
        em.persist(tag);
      }

      for (int i = 1; i < 10; i++) {
        Company company = new Company();
        CompanyHistory history = company.getLatest();
        history.setName("カンパニー" + i);
        history.setUrl("http://company" + i + ".com");
        for (int j = 0; j < 3; j++) {
          company.getTagSet().add(tagList.get((int) Math.floor(Math.random() * 9)));
        }
        em.persist(company);

        for (int j = 1; j < 10; j++) {
          Service service = new Service();
          service.setName(String.format("サービス - %d-%d", i, j));
          service.setCompany(company);
          em.persist(service);
        }
      }

      Account account = new Account();
      account.setUsername("root");
      account.setPassword("root");
      em.persist(account);

      et.commit();
    } catch (Exception e) {
      et.rollback();
      throw e;
    }

  }

}
