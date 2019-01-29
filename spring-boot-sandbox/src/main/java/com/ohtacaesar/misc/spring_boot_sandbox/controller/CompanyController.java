package com.ohtacaesar.misc.spring_boot_sandbox.controller;

import com.ohtacaesar.misc.spring_boot_sandbox.CompanyRepository;
import com.ohtacaesar.misc.spring_boot_sandbox.NotFoundException;
import com.ohtacaesar.misc.spring_boot_sandbox.model.Company;
import com.ohtacaesar.misc.spring_boot_sandbox.model.CompanyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/companies")
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;

  @GetMapping
  public String index(Model model) {
    if (!model.containsAttribute("company")) {
      model.addAttribute("company", new Company());
    }

    model.addAttribute("companyList", companyRepository.findAll());

    return "company/index";
  }

  @GetMapping("/{companyId}")
  public String show(Model model, @PathVariable int companyId) {
    Company company = companyRepository.findOne(companyId);
    if (company == null) {
      throw new NotFoundException();
    }
    model.addAttribute("company", company);

    return "company/show";
  }

  @PostMapping
  public String create(
      @Validated Company company,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("company", company);
    } else {
      company.getHistory().setCompany(company);
      companyRepository.save(company);
    }
    return "redirect:/companies";
  }

  @PutMapping("/{companyId}")
  public String update(
      @PathVariable int companyId,
      @Validated Company posted,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    Company company = companyRepository.findOne(companyId);
    if (company == null) {
      throw new NotFoundException();
    }

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("company", company);
    } else {
      CompanyHistory history = posted.getHistory();
      company.setHistory(history);
      history.setId(0);
      history.setCompany(company);
      companyRepository.save(company);
    }

    return "redirect:/companies/" + companyId;
  }

}
