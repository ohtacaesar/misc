package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log
public class TestHandlerInterceptor implements HandlerInterceptor {

  @Autowired
  private HibernateInterceptor interceptor;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    log.info(Util.getThreadId() + ": " + getClass().getSimpleName() + "#preHandler");
    interceptor.init();
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {
    List<String> sqlList = interceptor.getSqlList();
    if (modelAndView != null) {
      modelAndView.addObject("sqlList", sqlList);
    }

    log.info(Util.getThreadId() + ": " + getClass().getSimpleName() + "#postHandler");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    interceptor.clear();
    log.info(String.format("%s: %s#afterCompletion: Transfer-Encoding=%s",
        Util.getThreadId(),
        getClass().getSimpleName(),
        response.getHeader("Transfer-Encoding"))
    );
  }
}
