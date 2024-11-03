package trantrongtin_iuh.baitap4_payara.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import trantrongtin_iuh.baitap4_payara.backend.repositories.entity.User;

import java.util.List;

public class UserRepository {
    @PersistenceContext(unitName = "baitap4_payara")
    private EntityManager entityManager;

    //lưu user vào database
    public void save(User user) {
        entityManager.persist(user);
    }
    //tìm user theo id
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }
    //tìm tất cả user
    public List<User> findAll() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    //xóa user
    public void delete(User user) {
        // Tìm lại thực thể từ EntityManager
        User managedUser = entityManager.find(User.class, user.getId());
        if (managedUser != null) {
            entityManager.remove(managedUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    //cập nhật user
    public void update(User user) {
        entityManager.merge(user);
    }
}
