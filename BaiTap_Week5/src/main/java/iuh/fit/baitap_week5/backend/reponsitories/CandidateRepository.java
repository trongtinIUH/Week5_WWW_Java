package iuh.fit.baitap_week5.backend.reponsitories;

import iuh.fit.baitap_week5.backend.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);

}