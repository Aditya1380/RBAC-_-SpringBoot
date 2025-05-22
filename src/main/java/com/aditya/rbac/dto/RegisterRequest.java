package com.aditya.rbac.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {	
	private String employeeName;
	private String employeePasssword;
	private String employeeDepartment;		
	private Set<String> roles;
}
