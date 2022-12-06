package rs.raf.domaci3.contollers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import rs.raf.domaci3.model.User;
import rs.raf.domaci3.repository.UserRepository;
import rs.raf.domaci3.request.LoginRequest;
import rs.raf.domaci3.response.LoginResponse;
import rs.raf.domaci3.service.AnotherUserDetailService;
import rs.raf.domaci3.utils.JwtUtils;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AnotherUserDetailService userService;
    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;
    public AuthController(AuthenticationManager authenticationManager, AnotherUserDetailService userService,
                          JwtUtils jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception   e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(new LoginResponse(
                jwtUtil.generateToken(userRepository.findByEmail(loginRequest.getEmail()))
                ));
    }

}
