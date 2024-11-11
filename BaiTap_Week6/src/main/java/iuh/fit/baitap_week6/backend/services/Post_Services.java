package iuh.fit.baitap_week6.backend.services;

import iuh.fit.baitap_week6.backend.models.Post;
import iuh.fit.baitap_week6.backend.models.PostComment;
import iuh.fit.baitap_week6.backend.reponsitories.PostComment_Reponsitory;
import iuh.fit.baitap_week6.backend.reponsitories.Post_Reponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

@Service
public class Post_Services {
    @Autowired
    private Post_Reponsitory postReponsitory;
    @Autowired
    private PostComment_Reponsitory postCommentReponsitory;

    public void save(Post post) {
        postReponsitory.save(post);
    }
    public List<Post> getAllPosts() {
        return postReponsitory.findAll();
    }


    public Optional<Post> findById(Long postId) {
        return postReponsitory.findById(postId);
    }

    public List<PostComment> getCommentsByPostId(Long postId) {
        return postCommentReponsitory.findByPost_Id(postId);
    }

    public void saveCmt(PostComment postcmt) {
        postCommentReponsitory.save(postcmt);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postReponsitory.findByAuthorId(userId);
    }
}
