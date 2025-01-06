
package cl.previred.taskmanagement.application.dto.request;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * Root Type for CrearTableroRequest
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "codigo",
    "nombre",
    "descripcion"
})
@Generated("jsonschema2pojo")
public class CrearTableroRequest {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigo")
    @NotNull(message = "El campo codigo no puede ser nulo")
    @NotEmpty(message = "El campo codigo no puede estar vacío")
    private String codigo;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("nombre")
    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotEmpty(message = "El campo nombre no puede estar vacío")
    private String nombre;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigo")
    public String getCodigo() {
        return codigo;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("codigo")
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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

    @JsonProperty("descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    @JsonProperty("descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        sb.append(CrearTableroRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("codigo");
        sb.append('=');
        sb.append(((this.codigo == null)?"<null>":this.codigo));
        sb.append(',');
        sb.append("nombre");
        sb.append('=');
        sb.append(((this.nombre == null)?"<null>":this.nombre));
        sb.append(',');
        sb.append("descripcion");
        sb.append('=');
        sb.append(((this.descripcion == null)?"<null>":this.descripcion));
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
        result = ((result* 31)+((this.codigo == null)? 0 :this.codigo.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nombre == null)? 0 :this.nombre.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CrearTableroRequest) == false) {
            return false;
        }
        CrearTableroRequest rhs = ((CrearTableroRequest) other);
        return (((((this.descripcion == rhs.descripcion)||((this.descripcion!= null)&&this.descripcion.equals(rhs.descripcion)))&&((this.codigo == rhs.codigo)||((this.codigo!= null)&&this.codigo.equals(rhs.codigo))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nombre == rhs.nombre)||((this.nombre!= null)&&this.nombre.equals(rhs.nombre))));
    }

}
