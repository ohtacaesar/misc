package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class Advice {

  @ModelAttribute("auth")
  Authentication authentication(Authentication authentication) {
    return authentication;
  }

}
