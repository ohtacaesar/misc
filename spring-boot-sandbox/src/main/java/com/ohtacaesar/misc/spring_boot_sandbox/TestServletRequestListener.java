package com.ohtacaesar.misc.spring_boot_sandbox;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import org.springframework.stereotype.Component;

@Component
public class TestServletRequestListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
  }
}
