package com.example.pagingandsorting.controller;

import com.example.pagingandsorting.entety.Student;
import com.example.pagingandsorting.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/{name}")
    public Student getStudentByName(@PathVariable String name){

        return studentRepo.findByName(name);
    }

    @GetMapping("/city/{city}")
    public List<Student> getStudentByCity(@PathVariable String city){

        return studentRepo.findByAddressCity(city);
    }

    @GetMapping("/subject/{sub}")
    public List<Student> getStudentBySubject(@PathVariable String sub){

        return studentRepo.findBySubjectsName(sub);
    }
}
