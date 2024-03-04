package cl.previred.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TAREAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tareas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_task")
    private String nameTask;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private EstadosTarea statusTask;
}
