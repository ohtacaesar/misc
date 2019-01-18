package com.ohtacaesar.misc.spring_boot_sandbox.sandbox;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/parameter")
public class ParameterController {

  @Data
  static class Test {

    private int test;
  }


  /**
   * GETパラメーターのtestに何かしら渡すと、o.testとtest両方に値が入る。文字列渡すとValidationエラーになる。
   *
   * <pre>
   * http://localhost:8080/parameter?test=1
   * => [{"test":1},1]
   * </pre>
   */
  @GetMapping
  @ResponseBody
  public List index(Test o, int test) {
    return Arrays.asList(o, test);
  }

}
