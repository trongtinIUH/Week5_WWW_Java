package iuh.fit.baitap_week6.frontend.controllers;

import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private User_Services user_services;


    @GetMapping("/List_user")
    public String listUser(Model model) {
        model.addAttribute("users", user_services.getAllUser());
        return "User_Screen/ListUser";
    }

    @GetMapping("/update-user")
    public String updateUser(Model model, @RequestParam("id") Long userId) {
        Optional<User> userOptional = user_services.findById(userId);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "User_Screen/UpdateUser";
        }
        model.addAttribute("errorMessage", "Không tìm thấy người dùng.");
        return "User_Screen/UpdateUser";
    }


    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        System.out.println("User ID: " + user.getId());
        System.out.println("Original Password: " + user.getPasswordHash());

        if (user.getId() == null) {
            redirectAttributes.addFlashAttribute("capnhatthatbai", "Cập nhật thất bại: ID không hợp lệ.");
            return "redirect:/update-user";
        }
        if (user.getRegisteredAt() == null) {
            user.setRegisteredAt(Instant.now()); // Hoặc một giá trị hợp lệ khác
        }

        try {
            // Lưu người dùng (mã hóa trong User_Services)
            user_services.save(user);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("capnhatthanhcong", "Cập nhật thành công!");
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("capnhatthatbai", "Cập nhật thất bại!");
            return "redirect:/update-user?id=" + user.getId();
        }
    }



    @GetMapping("/register")
    public String showRegisterForm() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("firstName") String firstName,
                               @RequestParam("password") String password,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("phone") String phone,
                               @RequestParam("email") String email,
                               Model model) {
        User user = new User();

        user.setPasswordHash(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMobile(phone);
        user.setEmail(email);
        user.setRegisteredAt(Instant.now());

        try {
            user_services.save(user);
            model.addAttribute("message", "Đăng ký thành công!");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Đăng ký thất bại!");
            return "register";
        }
    }







}
