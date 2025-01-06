
package cl.previred.taskmanagement.application.dto.request;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * Root Type for CrearTareaRequest
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nombre",
    "descripcion",
    "usuario",
    "codigoTablero"
})

@Generated("jsonschema2pojo")
public class CrearTareaRequest {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("nombre")
    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotEmpty(message = "El campo nombre no puede estar vacío")
    private String nombre;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("descripcion")
    @NotNull(message = "El campo descripcion no puede ser nulo")
    @NotEmpty(message = "El campo descripcion no puede estar vacío")
    private String descripcion;
    @JsonProperty("usuario")
    private String usuario;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigoTablero")
    @JsonPropertyDescription("")
    @NotNull(message = "El campo codigoTablero no puede ser nulo")
    @NotEmpty(message = "El campo codigoTablero no puede estar vacío")
    private String codigoTablero;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonProperty("usuario")
    public String getUsuario() {
        return usuario;
    }

    @JsonProperty("usuario")
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigoTablero")
    public String getCodigoTablero() {
        return codigoTablero;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigoTablero")
    public void setCodigoTablero(String codigoTablero) {
        this.codigoTablero = codigoTablero;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CrearTareaRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nombre");
        sb.append('=');
        sb.append(((this.nombre == null)?"<null>":this.nombre));
        sb.append(',');
        sb.append("descripcion");
        sb.append('=');
        sb.append(((this.descripcion == null)?"<null>":this.descripcion));
        sb.append(',');
        sb.append("usuario");
        sb.append('=');
        sb.append(((this.usuario == null)?"<null>":this.usuario));
        sb.append(',');
        sb.append("codigoTablero");
        sb.append('=');
        sb.append(((this.codigoTablero == null)?"<null>":this.codigoTablero));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.descripcion == null)? 0 :this.descripcion.hashCode()));
        result = ((result* 31)+((this.codigoTablero == null)? 0 :this.codigoTablero.hashCode()));
        result = ((result* 31)+((this.usuario == null)? 0 :this.usuario.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nombre == null)? 0 :this.nombre.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CrearTareaRequest) == false) {
            return false;
        }
        CrearTareaRequest rhs = ((CrearTareaRequest) other);
        return ((((((this.descripcion == rhs.descripcion)||((this.descripcion!= null)&&this.descripcion.equals(rhs.descripcion)))&&((this.codigoTablero == rhs.codigoTablero)||((this.codigoTablero!= null)&&this.codigoTablero.equals(rhs.codigoTablero))))&&((this.usuario == rhs.usuario)||((this.usuario!= null)&&this.usuario.equals(rhs.usuario))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nombre == rhs.nombre)||((this.nombre!= null)&&this.nombre.equals(rhs.nombre))));
    }

}
