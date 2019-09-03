package com.clayton.drools.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeObj {

	private Long id;
	private String name;
	private RoleObj role;
	private Integer companyTime;
}
