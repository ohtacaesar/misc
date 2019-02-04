package com.ohtacaesar.misc.spring_boot_sandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class NestedController {

  @RestController
  @RequestMapping("/a")
  static class AController {

    @GetMapping("")
    public String a() {
      return "a";
    }
  }

  @RestController
  @RequestMapping("/b")
  static class BController {

    @GetMapping("")
    public String b() {
      return "b";
    }
  }
}
