package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptor extends EmptyInterceptor {

  private ThreadLocal<List<String>> sqlList = new ThreadLocal<>();

  private Formatter formatter = FormatStyle.BASIC.getFormatter();

  public void init() {
    sqlList.set(new ArrayList<>());
  }

  public List<String> getSqlList() {
    return sqlList.get();
  }

  public void clear() {
    sqlList.remove();
  }

  @Override
  public String onPrepareStatement(String sql) {
    List<String> list = this.getSqlList();

    if (list != null) {
      list.add(formatter.format(sql));
    }
    return sql;
  }

  @Override
  public void afterTransactionBegin(Transaction tx) {
  }

  @Override
  public void afterTransactionCompletion(Transaction tx) {
  }

}
