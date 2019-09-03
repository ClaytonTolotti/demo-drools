package com.clayton.drools.obj;

public class EmployeeObj {

	private Long id;
	private String name;
	private RoleObj role;
	private Integer companyTime;
	public EmployeeObj() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleObj getRole() {
		return role;
	}

	public void setRole(RoleObj role) {
		this.role = role;
	}

	public Integer getCompanyTime() {
		return companyTime;
	}

	public void setCompanyTime(Integer companyTime) {
		this.companyTime = companyTime;
	}

	public EmployeeObj(Long id, String name,
			RoleObj role,
			Integer companyTime) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.companyTime = companyTime;
	}
}
