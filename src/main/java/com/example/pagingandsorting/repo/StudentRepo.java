package com.example.pagingandsorting.repo;

import com.example.pagingandsorting.entety.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String name);
}
