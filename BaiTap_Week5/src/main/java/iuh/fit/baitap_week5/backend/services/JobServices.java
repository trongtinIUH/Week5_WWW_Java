package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Candidate;
import iuh.fit.baitap_week5.backend.models.Job;
import iuh.fit.baitap_week5.backend.models.JobSkill;
import iuh.fit.baitap_week5.backend.reponsitories.JobRepository;
import iuh.fit.baitap_week5.backend.reponsitories.JobSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServices {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }


//    public List<Job> findSuggestedJobsForCandidate(Candidate candidate) {
//        return jobRepository.findJobsByCandidateSkills(candidate.getCandidateSkills());
//    }

    public List<Job> findSuggestedJobsForCandidate(Candidate candidate) {
        // Lấy danh sách skillId từ CandidateSkill của ứng viên
        List<Long> skillIds = candidate.getCandidateSkills().stream()
                .map(cs -> cs.getSkill().getId())
                .collect(Collectors.toList());

        // Truy vấn danh sách công việc dựa trên skillIds
        return jobRepository.findJobsBySkillIds(skillIds);
    }
}
