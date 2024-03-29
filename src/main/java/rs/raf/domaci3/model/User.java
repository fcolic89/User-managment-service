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
    private boolean canRead;
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;

    public User() {
    }

    public User(@NonNull String email, boolean canRead, boolean canCreate, boolean canUpdate, boolean canDelete) {
        this.email = email;
        this.canRead = canRead;
        this.canCreate = canCreate;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
    }
}
