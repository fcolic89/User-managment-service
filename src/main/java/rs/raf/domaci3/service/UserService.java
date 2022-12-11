package rs.raf.domaci3.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.raf.domaci3.model.User;
import rs.raf.domaci3.repository.UserRepository;
import rs.raf.domaci3.response.JwtResponse;
import rs.raf.domaci3.utils.JwtUtils;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
            || (!u.isPresent() && u1.isPresent())) {
            user.setPassword(u1.get().getPassword());
            return userRepository.save(user);
        }
        else return null;
    }

    @Override
    public JwtResponse update2(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        Optional<User> u1 = userRepository.findById(user.getId());
        if((u.isPresent() && u1.isPresent() && u.get().getId().longValue() == u1.get().getId().longValue())
                || (!u.isPresent() && u1.isPresent())) {
            user.setPassword(u1.get().getPassword());
             userRepository.save(user);
             return new JwtResponse(new JwtUtils().generateToken(user));
        }
        else return null;
    }

    @Override
    public User save(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(!u.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
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
