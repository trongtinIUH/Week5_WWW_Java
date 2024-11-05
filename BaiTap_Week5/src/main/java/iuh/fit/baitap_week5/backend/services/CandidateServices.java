package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Candidate;
import iuh.fit.baitap_week5.backend.models.Skill;
import iuh.fit.baitap_week5.backend.reponsitories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;
    //findall không phân trang
    public Page<Candidate> findAll(int pageNo,  int Pagesize, String sortBy, String sortDir){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(pageNo, Pagesize, sort);
        return candidateRepository.findAll(pageable);
    }


    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    public List<Candidate> findCandidatesBySkill(String skillName) {
        return candidateRepository.findCandidatesBySkill(skillName);
    }

    public List<Skill> getSuggestedSkillsForCandidate(String email) {
        return candidateRepository.getSuggestedSkillsForCandidate(email);
    }
}
