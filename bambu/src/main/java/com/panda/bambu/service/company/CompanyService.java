package com.panda.bambu.service.company;

import com.panda.bambu.model.User;
import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.company.Company;
import com.panda.bambu.model.company.CompanyRepository;
import com.panda.bambu.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	UserService userService;

	public Company findByNit(Long nit) {
		return companyRepository.findByNit(nit);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Boolean create(Long nit, String name, String social_reason, String direction, Long telephone, double famiEmpresa_id){
		Company company = companyRepository.findByNit(nit);
		if (company == null) {
			Company newCompany = new Company(nit, name, social_reason, direction, telephone);// ,famiEmpresa_id);
			companyRepository.save(newCompany);
			userService.addCompany(newCompany);
			return true;

		}
		return false;
	}

	public Boolean create(Company company) {
		Company compania = companyRepository.findByNit(company.getNit());
		if (compania == null) {
			companyRepository.save(company);
			//userService.addCompany(company);
			return true;

		}
		return false;
	}

	public boolean isCompanyAlreadyPresent(Company company) {
		Company companyFound = companyRepository.findByNit(company.getNit());
		if (companyFound == null)
			return false;
		return true;
	}

	public Boolean modify(Company company_new) {

		Company companyFound = companyRepository.findByNit(company_new.getNit());
		if (companyFound != null) {
			companyFound.setNit(company_new.getNit());
			companyFound.setName(company_new.getName());
			companyFound.setSocial_reason(company_new.getSocial_reason());
			companyFound.setDirection(company_new.getDirection());
			companyFound.setTelephone(company_new.getTelephone());
			// companyFound.setFamiEmpresa_id(company_new.getFamiEmpresa_id());
			companyRepository.save(companyFound);
			return true;
		}
		return false;
	}

	public void visualize_profile_company(Company company) {
		company.toString();
	}

	public Boolean modify(Long nit, String name, String social_reason, String direction, Long telephone,
			double famiEmpresa_id) {

		Company companyFound = companyRepository.findByNit(nit);
		if (companyFound != null) {
			if (nit > 0.0 && !social_reason.isEmpty() && !name.isEmpty() && !direction.isEmpty() && telephone > 0.0
					&& famiEmpresa_id > 0.0) {
				companyFound.setNit(nit);
				companyFound.setName(name);
				companyFound.setSocial_reason(social_reason);
				companyFound.setDirection(direction);
				companyFound.setTelephone(telephone);
				companyFound.setFamiEmpresa_id(famiEmpresa_id);
				companyRepository.save(companyFound);
				return true;
			}
		}
		return false;
	}

	public Boolean delete(Company company) {
		/*int a = 0;
		if (companyRepository.existsById(company.getNit())) {

			User user = new User();
			user = company.getUser();
			for (Company c : user.getCompanyList()) {

				if (c.getNit() == company.getNit()) {
					user.getCompanyList().remove(a);
				}
				a = a + 1;
			}
			companyRepository.delete(company);
			return true;
		}

		return false;*/
		companyRepository.delete(company);
		return true;
	}
}