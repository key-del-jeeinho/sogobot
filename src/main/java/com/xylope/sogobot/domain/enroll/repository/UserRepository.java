package com.xylope.sogobot.domain.enroll.repository;

import com.xylope.sogobot.domain.enroll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@Email String email);
    boolean existsById(Long id);
}
