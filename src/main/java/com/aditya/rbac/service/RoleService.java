package com.aditya.rbac.service;

import org.springframework.stereotype.Service;

import com.aditya.rbac.dto.RegisterRole;
import com.aditya.rbac.model.Role;
import com.aditya.rbac.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private RoleRepository roleRepository;

	public Role addRole(RegisterRole requestrole) {
		if (roleRepository.findByRoleName(requestrole.getRoleName()).isPresent()) {
			throw new RuntimeException("Role already exists");
		}
		Role savedRole = new Role();
		savedRole.setName(requestrole.getRoleName());
		return roleRepository.save(savedRole);
	}
}
