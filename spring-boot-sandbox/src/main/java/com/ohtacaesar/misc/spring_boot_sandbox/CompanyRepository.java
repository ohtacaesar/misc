package com.ohtacaesar.misc.spring_boot_sandbox;


import com.ohtacaesar.misc.spring_boot_sandbox.model.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

  Optional<Company> findById(Integer id);

}
