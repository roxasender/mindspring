package com.nexus.mindspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.mindspring.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByroleName(String roleName);
    boolean existsByroleName(String roleName);
}
