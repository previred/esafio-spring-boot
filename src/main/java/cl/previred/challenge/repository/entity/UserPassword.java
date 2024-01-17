package cl.previred.challenge.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_password")
public class UserPassword {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public UserPassword setUser(User user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserPassword setPassword(String password) {
        this.password = password;
        return this;
    }
}
