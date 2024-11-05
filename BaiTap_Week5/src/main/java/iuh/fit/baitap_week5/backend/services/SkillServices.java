package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Skill;
import iuh.fit.baitap_week5.backend.reponsitories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillServices {
    @Autowired
    private SkillRepository skillRepository;





    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }
}
