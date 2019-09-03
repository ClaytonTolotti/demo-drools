package com.clayton.drools.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

	@JsonInclude(value = Include.NON_ABSENT)
	private Long id;
	
	private String name;
	
	private RoleDTO role;
	
	private Integer companyTime;
}
