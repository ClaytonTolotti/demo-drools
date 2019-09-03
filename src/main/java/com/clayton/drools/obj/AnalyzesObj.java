package com.clayton.drools.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzesObj {

    private Long id;
    private EmployeeObj employee;
    private RoleObj roleTarget;
    private List<Integer> companyTimeTarget;
    private Boolean validate;
}