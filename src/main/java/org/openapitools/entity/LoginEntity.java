package org.openapitools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login", schema = "public")
public class LoginEntity {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loginId;
    private String email;
    private String password;
}
