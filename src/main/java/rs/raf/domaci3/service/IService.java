package rs.raf.domaci3.service;

import rs.raf.domaci3.model.User;

import java.util.List;

public interface IService {
    List<User> findAll();
    void deleteById(Long id);
    User update(User user);

    User save(User user);

    User findById(Long id);

    User findByEmail(String email);


}
