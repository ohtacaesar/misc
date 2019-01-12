package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private TagRepository tagRepository;

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/items")
  public String itemIndex(Model model) {
    model.addAttribute("itemList", itemRepository.findAll());

    return "item/index";
  }

  @GetMapping("/items/{itemId}")
  public String itemShow(Model model, @PathVariable int itemId) {
    Item item = itemRepository.findOne(itemId);
    model.addAttribute("item", item);

    return "item/show";
  }

  @GetMapping("/tags")
  public String tagIndex(Model model) {
    model.addAttribute("tagList", tagRepository.findAll());

    return "tag/index";
  }
}
