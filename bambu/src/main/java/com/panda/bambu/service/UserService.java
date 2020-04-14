package com.panda.bambu.service;


//import com.panda.bambu.exception.UsernameOrIdNotFound;
//import com.panda.bambu.dto.ChangePasswordForm;
import com.panda.bambu.model.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
}
