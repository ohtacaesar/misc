package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tags")
public class TagController {

  @Autowired
  private TagRepository tagRepository;

  @GetMapping("")
  public String index(Model model) {
    model.addAttribute("tagList", tagRepository.findAll());

    return "tag/index";
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Iterable<Tag> indexJson() {
    return tagRepository.findAll();
  }

  @GetMapping("/{tagId}")
  public String show(Model model, @PathVariable int tagId) {
    model.addAttribute("tag", tagRepository.findOne(tagId));

    return "tag/show";
  }

  @GetMapping(value = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Tag showJson(@PathVariable int tagId) {
    return tagRepository.findOne(tagId);
  }
}
