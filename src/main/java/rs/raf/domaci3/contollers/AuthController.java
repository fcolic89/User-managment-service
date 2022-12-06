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
import rs.raf.domaci3.service.UserService;
import rs.raf.domaci3.utils.JwtUtils;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final UserService userService;
    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtils jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception   e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
        System.out.println("email iz kontolera: " + loginRequest.getEmail());
        System.out.println(userService.findByEmail(loginRequest.getEmail()).toString());
        return ResponseEntity.ok(new LoginResponse(
                jwtUtil.generateToken(userService.findByEmail(loginRequest.getEmail()))
                ));
    }
    @GetMapping(value = "test")
    public ResponseEntity<?> test(){
        System.out.println("test");
        return ResponseEntity.status(200).build();
    }

}
