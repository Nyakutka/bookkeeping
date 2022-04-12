package com.tsapov.bookkeeping.entity;

import com.tsapov.bookkeeping.repository.DepartmentRepository;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 200, message = "First name should be between 2 and 200 characters")
    @Column(name = "firstName", length = 20, nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 20 characters")
    @Column(name = "lastName", length = 20, nullable = false)
    private String lastName;

    @Column(name = "patherName", length = 20)
    private String patherName;

    @NotEmpty(message = "Position should not be empty")
    @Size(min = 2, max = 30, message = "position should be between 2 and 20 characters" )
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    @Min(value = 0, message = "Salary should be greater than 0")
    @Column(name = "salary", precision = 18, scale = 2, nullable = false)
    private BigDecimal salary;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "departments_employees",
               joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    private Department department;

    public Department getDepartment() {
        return department;
    }
}
