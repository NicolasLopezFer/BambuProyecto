package com.panda.bambu.service.user;
import com.panda.bambu.model.company.Company;
import com.panda.bambu.service.company.CompanyService;

//import com.panda.bambu.exception.UsernameOrIdNotFound;
//import com.panda.bambu.dto.ChangePasswordForm;
import com.panda.bambu.model.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);

	public Boolean addCompany(Company company) {
       
        if(companyService.isCompanyAlreadyPresent(company) == true){
            if(company !=null){
                User user = new User();
                user.getcompanyList().add(company);
                return true;            
            }
        }
        return false;    
	}
    
}
