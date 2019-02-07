package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.LinkedHashSet;
import java.util.Set;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class ThymeleafDialect extends AbstractDialect {

  public static final String PREFIX = "test";

  @Override
  public Set<IProcessor> getProcessors() {
    final Set<IProcessor> processors = new LinkedHashSet<>();
    processors.add(new Nl2brTextAttrProcessor());

    return processors;
  }

  @Override
  public String getPrefix() {
    return PREFIX;
  }
}
