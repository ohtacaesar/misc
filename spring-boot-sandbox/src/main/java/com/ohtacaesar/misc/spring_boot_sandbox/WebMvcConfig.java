package com.ohtacaesar.misc.spring_boot_sandbox;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(testHandlerInterceptor())
        .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
        .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
  }

  @Autowired
  private TemplateResolver templateResolver;

  @Bean
  public SpringTemplateEngine templateEngine() {
    System.out.println("create templateEngine");
    System.out.println(templateResolver);
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addDialect(new ThymeleafDialect());
    templateEngine.addDialect(new LayoutDialect());
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }

  @Bean
  public TestHandlerInterceptor testHandlerInterceptor() {
    return new TestHandlerInterceptor();
  }

}
