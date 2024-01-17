package cl.previred.challenge.repository.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_status")
public class TaskStatus {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public TaskStatus setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TaskStatus setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus setDescription(String description) {
        this.description = description;
        return this;
    }
}
