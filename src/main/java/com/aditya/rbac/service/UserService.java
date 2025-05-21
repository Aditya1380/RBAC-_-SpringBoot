package com.aditya.rbac.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya.rbac.dto.RegisterRequest;
import com.aditya.rbac.model.Employee;
import com.aditya.rbac.model.Role;
import com.aditya.rbac.repository.EmployeeRepository;
import com.aditya.rbac.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private EmployeeRepository employeeRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public Employee registerEmployee(RegisterRequest request) {
		if (employeeRepository.findByEmployeeName(request.getEmployeeName()).isPresent()) {
			throw new RuntimeException("EmployeeName Already Exist");
		}
		Employee employee = new Employee();
		employee.setEmployeeDepartment(request.getEmployeeDepartment());
		employee.setEmployeeName(request.getEmployeeName());
		Set<Role> roles = request.getRoles().stream()
				.map(roleName -> roleRepository.findByRoleName(roleName)
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
				.collect(Collectors.toSet());

		employee.setRoles(roles);

		Employee savedEmployee = employeeRepository.save(employee);
		savedEmployee.setEmployeeNo((int) (savedEmployee.getId() + 100));

		return employeeRepository.save(savedEmployee);
	}

}
