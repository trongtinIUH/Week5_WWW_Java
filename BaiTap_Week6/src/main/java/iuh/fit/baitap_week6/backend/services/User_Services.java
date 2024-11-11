package iuh.fit.baitap_week6.backend.services;

import ch.qos.logback.core.encoder.Encoder;
import iuh.fit.baitap_week6.backend.models.User;
import iuh.fit.baitap_week6.backend.reponsitories.User_Reponsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class User_Services {
    @Autowired
    private User_Reponsitory user_reponsitory;


    public List<User> getAllUser() {
        return user_reponsitory.findAll();
    }

    @Transactional
    public void save(User user) {
        user_reponsitory.save(user);
    }

    public Optional<User> findByMobile(String email) {
        return user_reponsitory.findByMobile(email);
    }


    public Optional<User> findById(Long userid) {
        return user_reponsitory.findById(userid);
    }
}
