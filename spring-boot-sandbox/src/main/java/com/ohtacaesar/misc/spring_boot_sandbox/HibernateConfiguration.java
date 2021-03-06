package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class HibernateConfiguration extends HibernateJpaAutoConfiguration {

  @Autowired
  private HibernateInterceptor hibernateInterceptor;

  public HibernateConfiguration(
      DataSource dataSource,
      JpaProperties jpaProperties,
      ObjectProvider<JtaTransactionManager> jtaTransactionManager,
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers
  ) {
    super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers);
  }

  @Override
  protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
    super.customizeVendorProperties(vendorProperties);
    vendorProperties.put("hibernate.ejb.interceptor", hibernateInterceptor);
  }
}
