package com.ohtacaesar.misc.spring_boot_sandbox;


import com.ohtacaesar.misc.spring_boot_sandbox.model.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

  Optional<Company> findById(Integer id);

  @EntityGraph(value = "Company")
  List<Company> findAll();

}
