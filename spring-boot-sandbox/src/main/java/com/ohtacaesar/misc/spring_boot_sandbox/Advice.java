package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Log
public class Advice implements ResponseBodyAdvice<Object> {

  @Autowired
  private HibernateInterceptor interceptor;

  @ModelAttribute("auth")
  Authentication authentication(Authentication authentication) {
    return authentication;
  }

  @Override
  public boolean supports(
      final MethodParameter returnType,
      final Class<? extends HttpMessageConverter<?>> converterType
  ) {
    log.info(Util.getThreadId() + ": " + getClass().getSimpleName() + "#supports");
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      final Object body,
      final MethodParameter returnType,
      final MediaType selectedContentType,
      final Class<? extends HttpMessageConverter<?>> selectedConverterType,
      final ServerHttpRequest request,
      final ServerHttpResponse response
  ) {
    log.info(Util.getThreadId() + ": " + getClass().getSimpleName() + "#bodyBeforeWrite");
    List<String> sqlList = interceptor.getSqlList();
    if (sqlList != null) {
      log.info(String.format("sqlList.size() == %d", sqlList.size()));
      response.getHeaders().add("X-QUERY-COUNT", String.valueOf(sqlList.size()));
    }
    return body;
  }
}
