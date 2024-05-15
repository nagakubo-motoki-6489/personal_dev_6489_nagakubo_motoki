package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email, String password);
	
	List<User> findByNameAndEmailAndPassword(String name, String email, String password);
}
