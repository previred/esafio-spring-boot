package cl.previred.taskmanagement.core.domain.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Rol")
public class Rol implements Serializable {

    @Id
    @Column(length = 5)
    private String codigo;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioRol> usuarios;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<UsuarioRol> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioRol> usuarios) {
        this.usuarios = usuarios;
    }
}