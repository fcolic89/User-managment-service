package rs.raf.domaci3.service;

import rs.raf.domaci3.model.User;
import rs.raf.domaci3.response.JwtResponse;

import java.util.List;

public interface IService {
    List<User> findAll();
    void deleteById(Long id);
    JwtResponse update(User user);
    User save(User user);
    User findById(Long id);
    User findByEmail(String email);


}
