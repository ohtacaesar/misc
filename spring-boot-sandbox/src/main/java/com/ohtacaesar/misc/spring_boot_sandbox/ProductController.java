package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    if (!model.containsAttribute("product")) {
      model.addAttribute("product", new Product());
    }

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
  @ResponseBody
  public Product showJson(@PathVariable int productId) {
    return productRepository.findOne(productId);
  }

  @PostMapping
  public String create(
      Model model,
      // @Validatedつけるとエラーページに飛ばない
      @Validated Product product,
      BindingResult bindingResult
  ) {

    if (bindingResult.hasErrors()) {
      return index(model);
    }

    productRepository.save(product);

    return "redirect:/products";
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object createJson(
      @RequestBody @Validated Product product,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return bindingResult.getAllErrors();
    }

    return productRepository.save(product);
  }

}
