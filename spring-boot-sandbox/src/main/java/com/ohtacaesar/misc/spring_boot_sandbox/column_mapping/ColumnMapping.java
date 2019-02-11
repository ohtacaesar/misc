package com.ohtacaesar.misc.spring_boot_sandbox.column_mapping;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ColumnMapping {

  @Id
  @GeneratedValue
  private int id;

  private byte byteColumn;

  private short shortColumn;

  private int intColumn;

  private long longColumn;

  private float floatColumn;

  private double doubleColumn;

  private boolean booleanColumn;

  private char charColumn;

  private char[] charArrayColumn;

  private String stringColumn;

  private Date dateColumn;

  private LocalDate localDateColumn;


}
