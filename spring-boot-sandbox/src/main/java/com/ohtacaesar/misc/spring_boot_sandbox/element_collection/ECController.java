package com.ohtacaesar.misc.spring_boot_sandbox.element_collection;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/element-collection")
@Log4j
public class ECController {

  @Autowired
  private ECEntityRepository repository;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("list", repository.findAll());

    return "element_collection/index";
  }

  @PostConstruct
  private void postConstruct() {
    List<ECEntity> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ECEntity o = new ECEntity();
      for (int j = 0; j < 5; j++) {
        o.getA().add("a" + j);
        o.getB().add("b" + j);
      }
      list.add(o);
    }
    repository.save(list);
  }

}
