package com.ohtacaesar.misc.spring_boot_sandbox;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import org.springframework.stereotype.Component;

@Component
public class TestServletRequestListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    System.out.println(getClass() + "#requestDestroyed");
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    System.out.println(getClass() + "#requestInitialized");
  }
}
