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
    private int canRead;
    private int canCreate;
    private int canUpdate;
    private int canDelete;

    public User() {
    }

    public User(@NonNull String email, int canRead, int canCreate, int canUpdate, int canDelete) {
        this.email = email;
        this.canRead = canRead;
        this.canCreate = canCreate;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
    }
}
