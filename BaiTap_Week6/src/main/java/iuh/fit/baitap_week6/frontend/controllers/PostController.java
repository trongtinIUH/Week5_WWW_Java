package iuh.fit.baitap_week6.frontend.controllers;

import iuh.fit.baitap_week6.backend.models.Post;
import iuh.fit.baitap_week6.backend.models.PostComment;
import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.services.PostComment_Services;
import iuh.fit.baitap_week6.backend.services.Post_Services;
import iuh.fit.baitap_week6.backend.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private Post_Services postServices;

    @Autowired
    private User_Services userServices;

    @Autowired
    private PostComment_Services postCommentServices;


    @GetMapping("/post/my-post")
    public String showMyListPost(Model model, @RequestParam("userId") Long userId) {
        model.addAttribute("myList", postServices.getPostsByUserId(userId));

        Optional<User> optionalUser = userServices.findById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        } else {
            model.addAttribute("error", "Không tìm thấy người dùng.");
        }

        return "BaiPost/MyPost";
    }



    @GetMapping("/post/list-posts")
    public String showListpost(Model model, @RequestParam("userId") Long userId) {
        model.addAttribute("posts", postServices.getAllPosts()); // Lấy tất cả các bài viết và đưa vào model
        Optional<User> optionalUser = userServices.findById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        } else {
            model.addAttribute("error", "Không tìm thấy người dùng.");
        }
        return "BaiPost/ListPosts"; // Trả về trang hiển thị danh sách bài viết
    }



    @GetMapping("/post/comment/add")
    public String showAddCommentForm(@RequestParam("postId") Long postId, Model model) {
        Optional<Post> optionalPost = postServices.findById(postId); // Lấy post theo ID
        if (optionalPost.isPresent()) {
            model.addAttribute("post", optionalPost.get());
            model.addAttribute("comments", postServices.getCommentsByPostId(postId)); // Lấy danh sách comment
        } else {
            model.addAttribute("error", "Không tìm thấy bài viết.");
        }
        return "BaiPost/Comment";
    }

    @PostMapping("/post/comment/add")
    public String addNewPostComment(
            @RequestParam("postId") Long postId,
            @RequestParam("title") String title,
            @RequestParam(value = "published", required = false) boolean published,
            Model model) {

        // Tìm post theo postId
        Optional<Post> optionalUser = postServices.findById(postId);
        if (optionalUser.isPresent()) {
            Post author = optionalUser.get();

            PostComment postcmt = new PostComment();
            postcmt.setTitle(title);
            postcmt.setPublished(published);
            postcmt.setCreatedAt(Instant.now());
            postcmt.setPost(author);  // Thiết lập author cho bài viết

            // Lưu cmt
            postServices.saveCmt(postcmt);

            model.addAttribute("message", "Bài viết đã được đăng thành công!");
            return "redirect:/home";  // Chuyển hướng về trang chủ hoặc danh sách bài viết
        } else {
            model.addAttribute("error", "Không tìm thấy người dùng.");
            return "BaiPost/CreatePost";
        }
    }


    @GetMapping("/post/create")
    public String showCreatePostForm(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("userId", userId);
        return "BaiPost/CreatePost";
    }

    @PostMapping("/post/create")
    public String addNewPost(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "published", required = false) boolean published,
            Model model) {

        // Tìm User theo userId
        Optional<User> optionalUser = userServices.findById(userId);
        if (optionalUser.isPresent()) {
            User author = optionalUser.get();

            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setPublished(published);
            post.setCreatedAt(Instant.now());
            post.setAuthor(author);  // Thiết lập author cho bài viết

            postServices.save(post);  // Lưu bài viết

            model.addAttribute("message", "Bài viết đã được đăng thành công!");
            return "redirect:/home";  // Chuyển hướng về trang chủ hoặc danh sách bài viết
        } else {
            model.addAttribute("error", "Không tìm thấy người dùng.");
            return "BaiPost/CreatePost";
        }
    }

}
