package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("productList", productRepository.findAll());

    return "product/index";
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Product> indexJson() {
    return productRepository.findAll();
  }

  @GetMapping("/{productId}")
  public String show(Model model, @PathVariable int productId) {
    model.addAttribute("product", productRepository.findOne(productId));

    return "product/show";
  }

  @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Product showJson(@PathVariable int productId) {
    return productRepository.findOne(productId);
  }

}
