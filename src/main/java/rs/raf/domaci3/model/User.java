package rs.raf.domaci3.model;


import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String lastname;
    private Boolean canRead;
    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canDelete;

    public User() {
    }

}
