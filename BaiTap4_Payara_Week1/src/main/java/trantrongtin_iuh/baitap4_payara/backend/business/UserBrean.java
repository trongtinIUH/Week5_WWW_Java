package trantrongtin_iuh.baitap4_payara.backend.business;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import trantrongtin_iuh.baitap4_payara.backend.repositories.UserRepository;
import trantrongtin_iuh.baitap4_payara.backend.repositories.entity.User;

import java.util.List;

@Stateless
public class UserBrean implements UserBeanRemote {

    @Inject
    private UserRepository userRepository;


    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
