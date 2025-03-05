package com.meme.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meme.first.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
