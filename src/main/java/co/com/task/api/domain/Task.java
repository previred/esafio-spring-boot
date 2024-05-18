package co.com.task.api.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks", schema = "public")
public class Task implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_task")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTask;

    @NotEmpty(message = "La descripcion no puede estar vacia")
    @Size(max = 100, message = "El descripcion no puede tener más de 100 caracteres")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message = "El status no puede estar vacia")
    @Size(max = 10, message = "El status no puede tener más de 10 caracteres")
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;

    @JoinColumn(name = "id_user")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @PrePersist
    public void prePersist() {
	created = LocalDateTime.now();
	modified = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
	modified = LocalDateTime.now();
    }
}
