package cl.dpichinil.desafio.desafiospringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tb_user", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "usr_username", unique = true, length = 30)
    private String username;
    @Column(name = "usr_password", length = 200)
    private String password;
    @Column(name = "last_password_change")
    private Date lastPasswordChange;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_access")
    private Date lastAccess;
    @Column(name = "active")
    private boolean active;
    @Column(name = "expired")
    private boolean expired;
    @Column(name = "locked")
    private boolean locked;
    @Column(name = "authorities_text")
    private String authoritiesText;
}
