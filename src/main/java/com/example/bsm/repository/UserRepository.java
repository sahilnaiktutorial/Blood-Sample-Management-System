package com.example.bsm.repository;

import com.example.bsm.entity.User;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByAvailableCityInAndBloodGroupIn(List<String> cities, List<BloodGroup> bloodGroups);

    Optional<User> findByEmail(String username);
}
