package com.clayton.drools.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnalyzesDTO {

    private Long id;

    private EmployeeDTO employee;

    private RoleDTO roleTarget;

    private List<Integer> companyTimeTarget;

    private Boolean validate;
}
