package iuh.fit.baitap_week6.frontend.controllers;

import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private User_Services userServices;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Tên template cho trang đăng nhập
    }

    @RequestMapping("/home")
    public String showHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mobile = auth.getName(); // Lấy số điện thoại của người dùng đã xác thực

        Optional<User> optionalUser = userServices.findByMobile(mobile); // Tìm người dùng theo số điện thoại
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLastLogin(Instant.now()); // Cập nhật thời gian đăng nhập cuối
            userServices.save(user);
            model.addAttribute("user", user);
            model.addAttribute("loginSuccess", "Đăng nhập thành công!"); // Thêm thông báo thành công
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy người dùng.");
            return "redirect:/login"; // Điều hướng lại trang đăng nhập nếu người dùng không tồn tại
        }

        return "home"; // Điều hướng đến trang chính
    }




    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Điều hướng lại trang đăng nhập sau khi logout
    }
}
