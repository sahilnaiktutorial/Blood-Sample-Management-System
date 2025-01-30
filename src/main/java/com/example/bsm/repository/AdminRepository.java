package com.example.bsm.repository;

import com.example.bsm.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    public Optional<Admin> findByEmail_User(String username);
}
