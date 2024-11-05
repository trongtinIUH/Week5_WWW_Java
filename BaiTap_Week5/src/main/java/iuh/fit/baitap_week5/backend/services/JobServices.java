package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Job;
import iuh.fit.baitap_week5.backend.models.JobSkill;
import iuh.fit.baitap_week5.backend.reponsitories.JobRepository;
import iuh.fit.baitap_week5.backend.reponsitories.JobSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServices {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }



}
