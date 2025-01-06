package cl.previred.taskmanagement.core.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Usuario_Rol", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"codigo_rol", "rut"})
})
public class UsuarioRol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_UsuarioRol_Rol"))
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "rut", nullable = false, foreignKey = @ForeignKey(name = "FK_UsuarioRol_Usuario"))
    private Usuario usuario;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsuarioRol{");
        sb.append("codigo=").append(codigo);
        sb.append(", rol=").append(rol);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}
