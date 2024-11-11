package iuh.fit.baitap_week6.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "middleName", length = 50)
    private String middleName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "passwordHash", nullable = false, length = 64)
    private String passwordHash;

    @Column(name = "registeredAt", nullable = false)
    private Instant registeredAt;

    @Column(name = "lastLogin")
    private Instant lastLogin;

    @Lob
    @Column(name = "intro")
    private String intro;

    @Lob
    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String mobile, String email, String passwordHash, Instant registeredAt, Instant lastLogin, String intro, String profile) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.intro = intro;
        this.profile = profile;
    }


}