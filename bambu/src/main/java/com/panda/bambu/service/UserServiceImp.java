package com.panda.bambu.service;

import java.util.Arrays;
import java.util.HashSet;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.RoleRepository;
//import com.panda.bambu.exception.CustomeFieldValidationException;
//import com.panda.bambu.exception.UsernameOrIdNotFound;
//import com.panda.bambu.dto.ChangePasswordForm;
import com.panda.bambu.model.User;
import com.panda.bambu.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("Finanzas Personales");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// Try to implement this method, as assignment.
		User userFound = userRepository.findByEmail(user.getEmail());
		if (userFound==null) 
			return false;
		System.out.println("no existe");
			return true;
	}
}
