package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("")
  public String home() {
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/security")
  public String security() {
    return "security";
  }

  @GetMapping("/admin")
  public String admin() {
    return "security";
  }
}
