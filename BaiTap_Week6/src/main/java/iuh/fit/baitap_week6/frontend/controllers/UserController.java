package iuh.fit.baitap_week6.frontend.controllers;

import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        System.out.println("User ID: " + user.getId()); // Kiểm tra ID người dùng
        System.out.println("Modified Password: " + user.getPasswordHash()); // Kiểm tra mật khẩu sau khi thay đổi

        if (user.getId() == null) {
            redirectAttributes.addFlashAttribute("capnhatthatbai", "Cập nhật thất bại: ID không hợp lệ.");
            return "redirect:/update-user"; // Điều hướng lại trang cập nhật mà không có id
        }

        try {
            // Xử lý thêm chuỗi `{troop}` vào trước mật khẩu
            String originalPassword = user.getPasswordHash();
            String modifiedPassword = "{noop}" + originalPassword;
            user.setPasswordHash(modifiedPassword);

            // Lưu người dùng
            user_services.save(user);

            // Thêm thông báo thành công vào RedirectAttributes
            redirectAttributes.addFlashAttribute("capnhatthanhcong", "Cập nhật thành công!");

            // Chuyển hướng về trang chủ sau khi cập nhật thành công
            return "redirect:/home";
        } catch (Exception e) {
            // Nếu có lỗi, thêm thông báo thất bại vào RedirectAttributes
            redirectAttributes.addFlashAttribute("capnhatthatbai", "Cập nhật thất bại!");
            return "redirect:/update-user?id=" + user.getId();
        }
    }









}
