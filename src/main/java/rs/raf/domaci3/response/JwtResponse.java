package rs.raf.domaci3.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
