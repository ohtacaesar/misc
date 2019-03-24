package com.ohtacaesar.misc.spring_boot_sandbox.element_collection;

import com.ohtacaesar.misc.spring_boot_sandbox.HibernateInterceptor;
import com.ohtacaesar.misc.spring_boot_sandbox.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j
public class ECScheduled {

  @Autowired
  private ECEntityRepository repository;

  @Autowired
  private HibernateInterceptor interceptor;

  @Scheduled(fixedDelay = 1000)
  @Transactional
  public void task() {
    interceptor.init();
    List<String> a = new ArrayList<>();
    List<String> b = new ArrayList<>();
    for (ECEntity ec : repository.findAll()) {
      a.addAll(ec.getA().stream().collect(Collectors.toList()));
      b.addAll(ec.getB().stream().collect(Collectors.toList()));
    }
    int sqlSize = interceptor.getSqlList().size();
    interceptor.clear();

    log.info(String.format(
        "%s: a.size() == %d, b.size() == %d, sqlList.size() == %d",
        Util.getThreadId(),
        a.size(),
        b.size(),
        sqlSize
    ));
  }

}
