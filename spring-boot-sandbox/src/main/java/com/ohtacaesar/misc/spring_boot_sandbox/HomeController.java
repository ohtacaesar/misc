package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/items")
  public String itemIndex(Model model) {
    model.addAttribute("itemList", itemRepository.findAll());

    return "item/index";
  }

  @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Iterable<Item> itemIndexJson() {
    return itemRepository.findAll();
  }

  @GetMapping("/items/{itemId}")
  public String itemShow(Model model, @PathVariable int itemId) {
    Item item = itemRepository.findOne(itemId);
    model.addAttribute("item", item);

    return "item/show";
  }

}
