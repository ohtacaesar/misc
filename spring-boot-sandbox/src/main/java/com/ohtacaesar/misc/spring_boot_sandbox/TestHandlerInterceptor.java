package com.ohtacaesar.misc.spring_boot_sandbox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TestHandlerInterceptor implements HandlerInterceptor {

  @Autowired
  private HibernateInterceptor interceptor;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    System.out.println(Util.getThreadId() + ": " + getClass().getSimpleName() + "#preHandler");
    interceptor.init();
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {
    modelAndView.addObject("sqlList", interceptor.getSqlList());
    System.out.println(Util.getThreadId() + ": " + getClass().getSimpleName() + "#postHandler");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    interceptor.clear();
    System.out.println(Util.getThreadId() + ": " + getClass().getSimpleName() + "#afterCompletion");
  }
}
