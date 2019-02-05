package com.ohtacaesar.misc.spring_boot_sandbox.dummy.repository;


import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

  @EntityGraph(value = "Index")
  List<Company> findAll();

  @EntityGraph(value = "Show")
  Optional<Company> findById(Integer id);

}
