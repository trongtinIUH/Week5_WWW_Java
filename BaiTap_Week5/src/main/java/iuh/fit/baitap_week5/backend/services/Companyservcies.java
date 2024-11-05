package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Company;
import iuh.fit.baitap_week5.backend.models.Skill;
import iuh.fit.baitap_week5.backend.reponsitories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Companyservcies {
    @Autowired
    private CompanyRepository companyRepository;

    public Company findByName(String compName) {
        return companyRepository.findByCompName (compName)
                .orElseGet(() -> {
                    Company newCompany = new Company();
                    newCompany.setCompName(compName);
                    return companyRepository.save(newCompany);
                });
    }
}
