package com.devland.assignment.assignment12.applicationuser;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);

    Optional<ApplicationUser> findByEmail(String email);
}