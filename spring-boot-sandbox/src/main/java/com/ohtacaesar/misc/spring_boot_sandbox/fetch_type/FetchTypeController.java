package com.ohtacaesar.misc.spring_boot_sandbox.fetch_type;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fetch-type")
public class FetchTypeController {

  @PersistenceContext
  private EntityManager em;

  @PersistenceUnit
  private EntityManagerFactory emf;

  @GetMapping
  public String index(Model model) {
    List<A> list = em.createQuery("select a from A a").getResultList();
    model.addAttribute("list", list);

    return "fetch_type/index";
  }

  @PostConstruct
  private void postConstruct() {
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();
    try {
      for (int i = 0; i < 3; i++) {
        A a = new A();
        for (int j = 0; j < 3; j++) {
          a.getBList().add(new B());
          a.getCList().add(new C());
          a.getDList().add(new D());
        }
        em.persist(a);
      }

      et.commit();
    } catch (Exception e) {
      et.rollback();
    }
  }
}
