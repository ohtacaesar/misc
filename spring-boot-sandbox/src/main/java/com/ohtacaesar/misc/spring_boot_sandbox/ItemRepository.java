package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.data.jpa.repository.JpaRepository;

interface ItemRepository extends JpaRepository<Item, Integer> {

}
