package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(testHandlerInterceptor())
        .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
        .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
  }

  @Bean
  public TestHandlerInterceptor testHandlerInterceptor() {
    return new TestHandlerInterceptor();
  }

}
