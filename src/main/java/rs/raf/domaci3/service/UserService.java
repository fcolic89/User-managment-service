package rs.raf.domaci3.service;

import org.springframework.stereotype.Service;
import rs.raf.domaci3.model.User;
import rs.raf.domaci3.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        Optional<User> u1 = userRepository.findById(user.getId());
        if((u.isPresent() && u1.isPresent() && u.get().getId().equals(u1.get().getId()))
            || (!u.isPresent() && u1.isPresent()))
            return userRepository.save(user);
        else return null;
    }

    @Override
    public User save(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(!u.isPresent())
            return userRepository.save(user);
        else return null;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user =  userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}
