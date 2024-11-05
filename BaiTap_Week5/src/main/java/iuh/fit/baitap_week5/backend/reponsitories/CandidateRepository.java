package iuh.fit.baitap_week5.backend.reponsitories;

import iuh.fit.baitap_week5.backend.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);

    @Query("SELECT c FROM Candidate c JOIN c.candidateSkills cs WHERE cs.skill.skillName LIKE %:skillName%")
    List<Candidate> findCandidatesBySkill(String skillName);
}