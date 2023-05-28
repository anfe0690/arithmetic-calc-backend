package io.github.anfe0690.arithcalcbackend.user;

import javax.persistence.*;

@Entity(name = "user_entity")
@Table(indexes = { @Index(name = "username_idx", columnList = "username", unique = true) })
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private String passwordHash;

    private String name;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String passwordHash, String name) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
