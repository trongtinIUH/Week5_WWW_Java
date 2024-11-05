package iuh.fit.baitap_week5.fontend.controllers;

import iuh.fit.baitap_week5.backend.models.Candidate;
import iuh.fit.baitap_week5.backend.models.Job;
import iuh.fit.baitap_week5.backend.reponsitories.CandidateRepository;
import iuh.fit.baitap_week5.backend.services.CandidateServices;
import iuh.fit.baitap_week5.backend.services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;

    @Autowired
    private JobServices jonServices;
    @Autowired
    private JobServices jobServices;

    @GetMapping("/listkpt")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }


    //findall có phân trang
    @GetMapping("/list")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateServices.findAll(currentPage - 1, pageSize, "id", "asc");

        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }



    @GetMapping("/candidate/login")
    public String showLoginForm() {
        return "login"; // tên template của trang đăng nhập
    }

    @PostMapping("/candidate/login")
    public String loginCandidate(@RequestParam("email") String email, Model model) {
        Candidate candidate = candidateServices.findByEmail(email);
        if (candidate == null) {
            model.addAttribute("error", "No candidate found with this email.");
            return "login";
        }

        // Lấy danh sách công việc gợi ý dựa trên kỹ năng của ứng viên
        List<Job> suggestedJobs = jobServices.findSuggestedJobsForCandidate(candidate);
        model.addAttribute("candidate", candidate);
        model.addAttribute("suggestedJobs", suggestedJobs);

        return "suggested-jobs"; // Trang hiển thị danh sách công việc
    }


    //câu 6 tìm ứng viên có skill phù hợp
    @GetMapping("/candidate/skill_search")
    public String showSkillSearchForm() {
        return "skill-search";
    }

    @PostMapping("/candidate/skill_search")
    public String searchCandidateBySkill(@RequestParam("skillName") String skillName, Model model) {
        List<Candidate> candidates = candidateServices.findCandidatesBySkill(skillName);
        model.addAttribute("UngVienPhuHop", candidates);
        model.addAttribute("skillName", skillName);
        return "sugggested-skill-search";
    }


}
