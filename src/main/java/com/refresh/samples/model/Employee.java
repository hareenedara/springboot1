package com.refresh.samples.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="EMPLOYEE")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First Name can not be blank")
    @Size(min=2)
    private String firstName;
    @NotEmpty(message="Last Name can not be blank")
    @Size(min=2)
    private String lastName;
    //@Min(value=18, message="Age is required and should be greater than or equal to 18")
    private int age;
    private String dept;
    @Email(message="Invalid email")
    private String email;
    @PastOrPresent(message = "Invalid startDate")
    private LocalDate startDate;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
