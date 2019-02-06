package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptor extends EmptyInterceptor {

  private ThreadLocal<List<String>> sqlList = new ThreadLocal<>();

  public void init() {
    System.out.println(getClass() + "#init: " + Thread.currentThread().getId());
    sqlList.set(new ArrayList<>());
  }

  public List<String> getSqlList() {
    return sqlList.get();
  }

  @Override
  public String onPrepareStatement(String sql) {
    System.out.println(getClass() + "#onPrepareStatement: " + Thread.currentThread().getId());
    System.out.println(sql);
    List<String> list = this.getSqlList();

    if (list != null) {
      list.add(sql);
    }
    return sql;
  }

}
