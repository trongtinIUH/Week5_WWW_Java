package iuh.fit.baitap_week5.backend.reponsitories;


import iuh.fit.baitap_week5.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findBySkillName(String skillName);

    Optional<Skill> findBySkillNameAndSkillDescriptionAndType(String s, String s1, byte b);


}
