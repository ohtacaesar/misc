package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private TagRepository tagRepository;

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
    model.addAttribute("tagList", tagRepository.findAll());

    return "product/show";
  }

  @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Product showJson(@PathVariable int productId) {
    return productRepository.findOne(productId);
  }

  @PostMapping
  public String create(
      // @Validatedつけるとエラーページに飛ばない
      @Validated Product product,
      BindingResult bindingResult,
      RedirectAttributes attributes
  ) {
    if (productRepository.findByName(product.getName()) != null) {
      bindingResult.addError(
          new FieldError(bindingResult.getObjectName(), "name", "a name must be unique")
      );
    }

    if (bindingResult.hasErrors()) {
      attributes.addFlashAttribute("product", product);
      attributes.addFlashAttribute(
          BindingResult.MODEL_KEY_PREFIX + bindingResult.getObjectName(),
          bindingResult
      );
      return "redirect:/products";
    }

    productRepository.save(product);

    return "redirect:/products";
  }

  @Transactional
  @PostMapping("/{productId}/tags")
  public String addTag(@PathVariable int productId, @RequestParam int tagId) {
    log.info("addTag(productId={}, tagId={})", productId, tagId);
    Product product = productRepository.findOne(productId);
    if (product == null) {
      throw new NotFoundException();
    }
    Tag tag = tagRepository.findOne(tagId);
    if (tag == null) {
      throw new NotFoundException();
    }

    product.getTagList().add(tag);

    return String.format("redirect:/products/%d", productId);
  }

  @Transactional
  @DeleteMapping("/{productId}/tags/{tagId}")
  public String removeTag(@PathVariable int productId, @PathVariable int tagId) {
    // Transactionalに任せると一度全関連DELETEしてから、再度登録しなおすっぽい
    log.info("removeTag(productId={}, tagId={})", productId, tagId);
    Product product = productRepository.findOne(productId);
    if (product == null) {
      throw new NotFoundException();
    }

    Optional<Tag> tag = product.getTagList().stream().filter(o -> o.getId() == tagId).findFirst();
    tag.ifPresent(tag1 -> product.getTagList().remove(tag1));

    return String.format("redirect:/products/%d", productId);
  }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object createJson(
      @RequestBody @Validated Product product,
      BindingResult bindingResult,
      HttpServletResponse response
  ) {
    if (bindingResult.hasErrors()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return bindingResult.getAllErrors();
    }

    return productRepository.save(product);
  }

}
