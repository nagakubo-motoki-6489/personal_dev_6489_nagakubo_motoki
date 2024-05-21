package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {
	
	List<Tasks> findByCategoryIdIs(Integer categoryId);
	
	List<Tasks> findByProgressIs(Integer progress);
	
	List<Tasks> findByProgressIsNot(Integer progress);
	
	List<Tasks> findAllByOrderByClosingDateAsc();
	
	List<Tasks> findByCategoryIdOrderByClosingDateAsc(Integer categoryId);
	
	List<Tasks> findAllByOrderByClosingDateDesc();
	
	List<Tasks> findByCategoryIdOrderByClosingDateDesc(Integer categoryId);
	
	
	
}
