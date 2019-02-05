package com.ohtacaesar.misc.spring_boot_sandbox.dummy.repository;

import com.ohtacaesar.misc.spring_boot_sandbox.dummy.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
