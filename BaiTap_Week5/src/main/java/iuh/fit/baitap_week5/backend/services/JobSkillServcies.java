package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.JobSkill;
import iuh.fit.baitap_week5.backend.reponsitories.JobSkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSkillServcies {
    @Autowired
    private  JobSkillRepository jobSkillRepository;

    @Transactional
    public JobSkill saveJobSkills(JobSkill jobSkills) {
        return jobSkillRepository.save(jobSkills);
    }
}
