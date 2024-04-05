package com.example.microservicedictionary.repository;

import com.example.microservicedictionary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User>findByNickname(final String nickname);
}
