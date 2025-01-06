
package cl.previred.taskmanagement.application.dto.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for RespuestaTableroDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "informacionTablero",
    "estado",
    "tareas"
})
@Generated("jsonschema2pojo")
public class RespuestaTableroDTO {

    /**
     * Root Type for TableroDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("informacionTablero")
    @JsonPropertyDescription("")
    private TableroDTO informacionTablero;
    /**
     * Root Type for Respuesta
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("estado")
    @JsonPropertyDescription("")
    private EstadoRespuestaDTO estado;
    /**
     * 
     */
    @JsonProperty("tareas")
    @JsonPropertyDescription("")
    private List<TareaDTO> tareas = new ArrayList<TareaDTO>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * Root Type for TableroDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("informacionTablero")
    public TableroDTO getInformacionTablero() {
        return informacionTablero;
    }

    /**
     * Root Type for TableroDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("informacionTablero")
    public void setInformacionTablero(TableroDTO informacionTablero) {
        this.informacionTablero = informacionTablero;
    }

    /**
     * Root Type for Respuesta
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("estado")
    public EstadoRespuestaDTO getEstado() {
        return estado;
    }

    /**
     * Root Type for Respuesta
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("estado")
    public void setEstado(EstadoRespuestaDTO estado) {
        this.estado = estado;
    }

    /**
     * 
     */
    @JsonProperty("tareas")
    public List<TareaDTO> getTareas() {
        return tareas;
    }

    /**
     * 
     */
    @JsonProperty("tareas")
    public void setTareas(List<TareaDTO> tareas) {
        this.tareas = tareas;
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
        sb.append(RespuestaTableroDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("informacionTablero");
        sb.append('=');
        sb.append(((this.informacionTablero == null)?"<null>":this.informacionTablero));
        sb.append(',');
        sb.append("estado");
        sb.append('=');
        sb.append(((this.estado == null)?"<null>":this.estado));
        sb.append(',');
        sb.append("tareas");
        sb.append('=');
        sb.append(((this.tareas == null)?"<null>":this.tareas));
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
        result = ((result* 31)+((this.estado == null)? 0 :this.estado.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.tareas == null)? 0 :this.tareas.hashCode()));
        result = ((result* 31)+((this.informacionTablero == null)? 0 :this.informacionTablero.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RespuestaTableroDTO) == false) {
            return false;
        }
        RespuestaTableroDTO rhs = ((RespuestaTableroDTO) other);
        return (((((this.estado == rhs.estado)||((this.estado!= null)&&this.estado.equals(rhs.estado)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.tareas == rhs.tareas)||((this.tareas!= null)&&this.tareas.equals(rhs.tareas))))&&((this.informacionTablero == rhs.informacionTablero)||((this.informacionTablero!= null)&&this.informacionTablero.equals(rhs.informacionTablero))));
    }

}
