package com.ohtacaesar.misc.spring_boot_sandbox.column_mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/column-mapping")
@Log
public class ColumnMappingController {

  @Autowired
  private DataSource dataSource;

  @GetMapping
  public String index(Model model) throws SQLException {

    List<Column> columnList = new ArrayList<>();
    model.addAttribute("columnList", columnList);

    try (
        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from column_mapping")
    ) {
      ResultSetMetaData metaData = rs.getMetaData();
      for (int i = 1, l = metaData.getColumnCount(); i <= l; i++) {
        Column column = new Column();
        column.setIndex(i - 1);
        column.setName(metaData.getColumnName(i));
        column.setScale(metaData.getScale(i));
        column.setPrecision(metaData.getPrecision(i));
        column.setDisplaySize(metaData.getColumnDisplaySize(i));
        column.setType(metaData.getColumnTypeName(i));
        columnList.add(column);
      }
    }

    return "column_mapping/index";
  }

  @Data
  static class Column {

    private int index;
    private String name;
    private String type;
    private int scale;
    private int precision;
    private int displaySize;


  }


}
