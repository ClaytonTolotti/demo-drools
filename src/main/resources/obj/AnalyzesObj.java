package com.clayton.drools.obj;

import java.util.List;

public class AnalyzesObj {

	private Long id;
	private EmployeeObj employee;
	private RoleObj roleTarget;
	private Boolean validate;

	private List<Integer> companyTimeTarget;

	public AnalyzesObj() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeObj getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeObj employee) {
		this.employee = employee;
	}

	public RoleObj getRoleTarget() {
		return roleTarget;
	}

	public void setRoleTarget(RoleObj roleTarget) {
		this.roleTarget = roleTarget;
	}

	public Boolean getValidate() {
		return validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

	public List<Integer> getCompanyTimeTarget() {
		return this.companyTimeTarget;
	}

	public void setCompanyTimeTarget(
			List<Integer> companyTimeTarget) {
		this.companyTimeTarget = companyTimeTarget;
	}

	public AnalyzesObj(Long id,
			EmployeeObj employee,
			RoleObj roleTarget,
			Boolean validate,
			List<Integer> companyTimeTarget) {
		this.id = id;
		this.employee = employee;
		this.roleTarget = roleTarget;
		this.validate = validate;
		this.companyTimeTarget = companyTimeTarget;
	}
}
