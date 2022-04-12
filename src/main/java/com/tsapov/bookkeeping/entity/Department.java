package com.tsapov.bookkeeping.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Department name should not be empty")
    @Size(min = 1, max = 20, message = "Department name should be between 1 and 20 characters")
    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
