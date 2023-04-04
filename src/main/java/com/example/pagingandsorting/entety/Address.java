package com.example.pagingandsorting.entety;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "address")
public class Address {

    @Id
    @SequenceGenerator(name = "address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "address_seq")
    private Long id;

    private String city;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private Student student;
}
