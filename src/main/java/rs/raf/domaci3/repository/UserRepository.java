package rs.raf.domaci3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.domaci3.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
    public void deleteById(Long id);

    public User findByEmail(String email);
}
