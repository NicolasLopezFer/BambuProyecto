package com.panda.bambu.service;
import com.panda.bambu.model.company.Company;


import com.panda.bambu.service.company.CompanyService;
import java.util.Arrays;
import java.util.HashSet;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.RoleRepository;
//import com.panda.bambu.exception.CustomeFieldValidationException;
//import com.panda.bambu.exception.UsernameOrIdNotFound;
//import com.panda.bambu.dto.ChangePasswordForm;
import com.panda.bambu.model.User;
import com.panda.bambu.model.UserRepository;
import com.panda.bambu.model.company.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.panda.bambu.model.User;


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
		User userFound = userRepository.findByEmail(user.getEmail());
		if (userFound==null) 
			return false;
		return true;
	}
	
	
	@Override
	public boolean addCompany(Company company){
		CompanyService newcompa =new CompanyService();
        if(newcompa.isCompanyAlreadyPresent(company)== true){
            if(company !=null){
                User user = new User();
                user.getCompanyList().add(company);
                return true;            
            }
        }
        return false;    
	}
    
}


