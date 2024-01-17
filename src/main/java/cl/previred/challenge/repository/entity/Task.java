package cl.previred.challenge.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatus status;

    public Task() {}

    public Task(String name, String description, LocalDateTime createdDate, User createdBy, User assignedTo, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    public Integer getId() {
        return id;
    }

    public Task setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Task setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Task setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Task setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Task setStatus(TaskStatus status) {
        this.status = status;
        return this;
    }
}
