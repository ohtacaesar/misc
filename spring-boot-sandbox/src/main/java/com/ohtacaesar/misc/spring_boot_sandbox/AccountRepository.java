package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, Integer> {

  Account findByUsername(String username);

}
