package com.example.pagingandsorting.repo;

import com.example.pagingandsorting.entety.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String name);
}
