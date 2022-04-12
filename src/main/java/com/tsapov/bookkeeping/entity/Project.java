package com.tsapov.bookkeeping.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "name should not be empty")
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Min(value = 0, message = "Salary should be greater than 0")
    @Column(name = "cost", precision = 18, scale = 2)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "dateBeg", nullable = false)
    private Date dateBeg;

    @Column(name = "dateEnd", nullable = false)
    private Date dateEnd;

    @Column(name = "dateEndReal", nullable = true)
    private Date dateEndReal = null;

    public Department getDepartment() {
        return department;
    }
}
