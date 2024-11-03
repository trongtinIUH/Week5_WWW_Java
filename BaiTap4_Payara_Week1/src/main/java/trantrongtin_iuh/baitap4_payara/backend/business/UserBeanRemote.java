package trantrongtin_iuh.baitap4_payara.backend.business;

import jakarta.ejb.Local;
import trantrongtin_iuh.baitap4_payara.backend.repositories.entity.User;

import java.util.List;

@Local
public interface UserBeanRemote {
    void add(User user);
    void update(User user);
    void delete(User user);
    User getUser(int id);
    List<User> getUsers();

}
