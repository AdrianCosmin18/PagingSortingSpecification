package com.example.pagingandsorting.entety;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "student")
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "student_seq")
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Subject> subjects;
}
