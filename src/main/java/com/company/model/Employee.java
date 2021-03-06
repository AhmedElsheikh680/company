package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name Cannot Be Empty")
    private String name;

    @Column(name = "code")
    @NotEmpty(message = "Code Cannot Be Empty")
    private String code;

    @Column(name="address")
    @NotEmpty(message = "Address Cannot Be Empty")
    private String address;
}
