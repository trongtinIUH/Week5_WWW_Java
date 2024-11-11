package iuh.fit.baitap_week6.backend.services;

import iuh.fit.baitap_week6.backend.models.Post;
import iuh.fit.baitap_week6.backend.reponsitories.Post_Reponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Post_Services {
    @Autowired
    private Post_Reponsitory postReponsitory;

    public void save(Post post) {
        postReponsitory.save(post);
    }
    public List<Post> getAllPosts() {
        return postReponsitory.findAll();
    }
}
