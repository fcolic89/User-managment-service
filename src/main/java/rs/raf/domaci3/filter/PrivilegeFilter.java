package rs.raf.domaci3.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.raf.domaci3.repository.UserRepository;
import rs.raf.domaci3.utils.JwtUtils;
import rs.raf.domaci3.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class PrivilegeFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public PrivilegeFilter(JwtUtils jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String email = null;
        if(authHeader != null) {
            if(authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                email = jwtUtil.extractUsername(jwt);
            }
        }
        else
            filterChain.doFilter(request, response);

        if (email != null) {
            Optional<User> u = userRepository.findByEmail(email);
            if(u.isPresent()){
                User user = u.get();
                String uri = request.getRequestURI();
                String method = request.getMethod();
                if((method.equals("GET") && user.isCanRead())
                    || (method.equals("POST") && user.isCanCreate())
                    || (method.equals("PUT") && user.isCanUpdate())
                    || (method.equals("DELETE") && user.isCanDelete()))
                    filterChain.doFilter(request,response);
                else
                    response.setStatus(403);
            }
            else
                response.setStatus(403);
        }
        else
            response.setStatus(403);
    }
}
