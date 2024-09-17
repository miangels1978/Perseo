package lkp.Perseo.services;

import lkp.Perseo.models.User;
import lkp.Perseo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public List<User> findAll() {
        return iUserRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    public User save(User user) {
        return iUserRepository.save(user);
    }

    public void deleteById(Long id) {
        iUserRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }
}
