package org.example.validateformregister.repository;

import org.example.validateformregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
