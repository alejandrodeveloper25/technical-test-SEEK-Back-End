package com.algonzjimz.tecnical_test.repository;

import com.algonzjimz.tecnical_test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
