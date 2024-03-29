package com.max.vault.repository;

import com.max.vault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Boolean existsByEmail(String email);
  Optional<User> findByAccountNumber(String accountNumber);
  Optional<User> findByEmail(String email);
}
