package com.aditya.rbac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.rbac.dto.RegisterRequest;
import com.aditya.rbac.dto.RegisterRole;
import com.aditya.rbac.model.Employee;
import com.aditya.rbac.model.Role;
import com.aditya.rbac.service.RoleService;
import com.aditya.rbac.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	private final RoleService roleService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerEmployee(@RequestBody RegisterRequest request){
		Employee employee = userService.registerEmployee(request);		
		return new ResponseEntity("Register Employee :"+employee.getEmployeeName(), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/add-role")
	public ResponseEntity<?> registerRole(@RequestBody RegisterRole requestrole) {
		try {
			Role role = roleService.addRole(requestrole);		
			return ResponseEntity.status(HttpStatus.CREATED)
				.body("New Role added was: " + role.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	
	
}
