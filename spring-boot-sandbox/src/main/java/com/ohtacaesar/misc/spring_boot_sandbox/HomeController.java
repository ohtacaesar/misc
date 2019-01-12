package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @Autowired
  private TagRepository tagRepository;

  @GetMapping("/")
  public String home(Model model, @RequestParam(defaultValue = "noname") String name) {
    model.addAttribute("name", name);

    return "home";
  }

  @GetMapping("/tags")
  public String tagIndex(Model model) {
    Tag tag = new Tag();
    tag.setName("test");
    tagRepository.save(tag);
    model.addAttribute("tags", tagRepository.findAll());

    return "tags/index";
  }
}
