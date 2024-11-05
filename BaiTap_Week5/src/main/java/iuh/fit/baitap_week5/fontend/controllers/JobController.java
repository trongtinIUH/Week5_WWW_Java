package iuh.fit.baitap_week5.fontend.controllers;

import iuh.fit.baitap_week5.backend.models.*;
import iuh.fit.baitap_week5.backend.services.Companyservcies;
import iuh.fit.baitap_week5.backend.services.JobServices;
import iuh.fit.baitap_week5.backend.services.JobSkillServcies;
import iuh.fit.baitap_week5.backend.services.SkillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {

    @Autowired
    private JobServices jobService;

    @Autowired
    private SkillServices skillService;

    @Autowired
    private Companyservcies companyService;

    @Autowired
    private JobSkillServcies jobSkillServices;

    @GetMapping("/job/post-job")
    public String showPostJobPage() {
        return "post-job";
    }

    @PostMapping("/job/create")
    public String createJob(@RequestParam("jobName") String jobName,
                            @RequestParam("jobDesc") String jobDesc,
                            @RequestParam("companyName") String companyName,
                            @RequestParam("skillName") String skillName,
                            @RequestParam("skillDescriptions") String skillDescriptions,
                            @RequestParam("types") Integer types,
                            @RequestParam("skillLevels") Integer skillLevels,
                            @RequestParam("moreInfos") String moreInfos,
                            Model model) {

        // Tìm hoặc tạo công ty dựa trên tên
        Company company = companyService.findByName(companyName);

        // Tạo và lưu công việc mới
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDesc(jobDesc);
        job.setCompany(company);
        jobService.saveJob(job);

        // Tạo và lưu kỹ năng cho công việc
        Skill skill = new Skill(skillName, skillDescriptions, types.byteValue());
        skillService.saveSkill(skill);

        // Tạo và lưu đối tượng JobSkill
        JobSkillId jobSkillId = new JobSkillId();
        jobSkillId.setJobId(job.getId());
        jobSkillId.setSkillId(skill.getId());

        JobSkill jobSkill = new JobSkill();
        jobSkill.setId(jobSkillId);
        jobSkill.setJob(job);
        jobSkill.setSkill(skill);
        jobSkill.setSkillLevel(skillLevels.byteValue());
        jobSkill.setMoreInfos(moreInfos);

        jobSkillServices.saveJobSkills(jobSkill);

        model.addAttribute("message", "Job created successfully!");
        return "post-job";
    }
}
