package iuh.fit.baitap_week6.backend.reponsitories;

import iuh.fit.baitap_week6.backend.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.List;

@Repository
public interface PostComment_Reponsitory extends JpaRepository<PostComment, Long> {


    List<PostComment> findByPost_Id(Long postId);
}
