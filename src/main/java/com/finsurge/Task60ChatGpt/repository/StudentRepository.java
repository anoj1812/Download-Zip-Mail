package com.finsurge.Task60ChatGpt.repository;

import com.finsurge.Task60ChatGpt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{ }
