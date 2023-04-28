package com.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
