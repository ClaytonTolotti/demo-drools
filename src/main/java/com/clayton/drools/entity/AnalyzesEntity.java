package com.clayton.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "analyzes")
public class AnalyzesEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee")
    private EmployeeEntity employee;

    @OneToOne
    @JoinColumn(name = "role_target")
    private RoleEntity roleTarget;

    private Integer companyTimeTarget;

    private Boolean validate;
}
