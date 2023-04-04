package com.example.pagingandsorting.controller;

import com.example.pagingandsorting.entety.Student;
import com.example.pagingandsorting.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @PostMapping("/specification")
    public List<Student> getStudentSpecif(){

        Specification<Student> specification = new Specification<>() {

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), "Cosmin");
                // vrem toti studentii care au numele = "Cosmin"
            }
        };

        List<Student> all = studentRepo.findAll(specification);

        return all;
    }
}
