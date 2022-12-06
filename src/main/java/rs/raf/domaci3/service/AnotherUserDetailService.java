package rs.raf.domaci3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.domaci3.model.User;
import rs.raf.domaci3.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AnotherUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public AnotherUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> u = userRepository.findByEmail(email);

        if(!u.isPresent()) {
            throw new UsernameNotFoundException("Email: "+email+" not found");
        }
        User user = u.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
