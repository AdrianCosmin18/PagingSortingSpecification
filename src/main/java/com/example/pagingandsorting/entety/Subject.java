package com.example.pagingandsorting.entety;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "subject")
public class Subject {

    @Id
    @SequenceGenerator(name = "subject_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "subject_seq")
    private Long id;

    private String name;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
