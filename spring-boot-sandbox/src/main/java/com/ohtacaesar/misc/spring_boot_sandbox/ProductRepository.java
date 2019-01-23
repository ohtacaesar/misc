package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Integer> {

  Product findByName(String name);

}
