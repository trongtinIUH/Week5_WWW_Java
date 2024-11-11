package iuh.fit.baitap_week6.backend.controllers;

import iuh.fit.baitap_week6.backend.models.Post;
import iuh.fit.baitap_week6.backend.services.Post_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Controller
public class PostController {

    @Autowired
    private Post_Services postServices;

    @GetMapping("/post/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "BaiPost/CreatePost";
    }

    @PostMapping("/post/add-Post")
    public String addNewPost(
            @RequestParam("author") String author,
            @RequestParam("parentId") String parent,
            @RequestParam("title") String title,
            @RequestParam("metaTitle") String metaTitle,
            @RequestParam("summary") String summary,
            @RequestParam("published") Instant published,
            @RequestParam("createdAt") Instant createdAt,
            @RequestParam("updatedAt") Instant updatedAt,
            @RequestParam("content") String  content,
           Model model) {
        //tìm user để thêm vào bài viết dựa theo tên user
        Post post = new Post();

        return "BaiPost/CreatePost";          // Chuyển hướng đến danh sách bài viết
    }
}
