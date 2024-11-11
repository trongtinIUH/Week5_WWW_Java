package iuh.fit.baitap_week6.backend.reponsitories;

import iuh.fit.baitap_week6.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Post_Reponsitory extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long userId);
}
