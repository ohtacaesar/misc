package com.ohtacaesar.misc.spring_boot_sandbox;

import org.springframework.data.jpa.repository.JpaRepository;

interface TagRepository extends JpaRepository<Tag, Integer> {

}
