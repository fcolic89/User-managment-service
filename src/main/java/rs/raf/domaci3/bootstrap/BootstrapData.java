package rs.raf.domaci3.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.raf.domaci3.model.User;
import rs.raf.domaci3.repository.UserRepository;

import java.util.Random;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public BootstrapData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Random rand = new Random();
        User user;

        for(int i = 0; i < 10; i++){
            user = new User();
            user.setEmail("user"+i+"@mail.com");
            user.setName("user"+i);
            user.setPassword(passwordEncoder.encode("user"+i));
            user.setLastname("user"+i+"ic");
            user.setCanRead(rand.nextInt(100)%2 == 0);
            user.setCanCreate(rand.nextInt(200)%2 == 0);
            user.setCanDelete(rand.nextInt(600)%2 == 0);
            user.setCanUpdate(rand.nextInt(300)%2 == 0);

            userRepository.save(user);
        }
    }
}
