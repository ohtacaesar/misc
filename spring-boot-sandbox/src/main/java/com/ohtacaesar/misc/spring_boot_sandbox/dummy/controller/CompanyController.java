package com.ohtacaesar.misc.spring_boot_sandbox.dummy.controller;

import com.ohtacaesar.misc.spring_boot_sandbox.NotFoundException;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Company;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.CompanyHistory;
import com.ohtacaesar.misc.spring_boot_sandbox.dummy.repository.CompanyRepository;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Company> indexJson() {
    return companyRepository.findAll();
  }

  @GetMapping("/{companyId}")
  public String show(Model model, @PathVariable int companyId) {
    //Company company = companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
    Company company = companyRepository.findOne(companyId);
    if (company == null) {
      throw new NotFoundException();
    }

    model.addAttribute("company", company);
    if (!model.containsAttribute("companyForm")) {
      model.addAttribute("companyForm", company);
    }

    return "company/show";
  }

  @PostMapping
  public String create(
      @Validated Company company,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      for (Entry<String, Object> entry : bindingResult.getModel().entrySet()) {
        redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue());
      }
    } else {
      companyRepository.save(company);
    }
    return "redirect:/companies";
  }

  @PutMapping("/{companyId}")
  public String update(
      @PathVariable int companyId,
      @ModelAttribute("companyForm") @Validated Company posted,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    Company company = companyRepository.findById(companyId).orElseThrow(NotFoundException::new);

    if (bindingResult.hasErrors()) {
      for (Entry<String, Object> entry : bindingResult.getModel().entrySet()) {
        redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue());
      }
    } else {
      CompanyHistory history = posted.getLatest();
      company.setLatest(history);
      companyRepository.save(company);
    }

    return "redirect:/companies/" + companyId;
    //return new RedirectView("redirect:/companies/" + companyId);
  }

  @DeleteMapping("/{companyId}")
  public String delete(@PathVariable int companyId) {
    Company company = companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
    companyRepository.delete(company);

    return "redirect:/companies";
  }

}
