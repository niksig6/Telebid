package com.example.nikson.repository;

import com.example.nikson.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //@Query("select u from User where u.id = :id") //jpql

    // TODO: Research how this works

    boolean existsByName(String name);

    Optional<User> findByName(String name);

    @Query("select u.name from User u where u.email = :#{#user.email} and u.password = :#{#user.password} and u.verified = true")
    Optional<String> findByEmailAndPassword(User user);

}