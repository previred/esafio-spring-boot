
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
 * Root Type for RespuestaDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "estado",
    "informacionTarea",
    "tareas"
})
@Generated("jsonschema2pojo")
public class RespuestaDTO {

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
     * Root Type for Tarea
     * <p>
     * 
     * 
     */
    @JsonProperty("informacionTarea")
    @JsonPropertyDescription("")
    private TareaDTO informacionTarea;
    /**
     * 
     */
    @JsonProperty("tareas")
    @JsonPropertyDescription("")
    private List<TareaDTO> tareas = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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
     * Root Type for Tarea
     * <p>
     * 
     * 
     */
    @JsonProperty("informacionTarea")
    public TareaDTO getInformacionTarea() {
        return informacionTarea;
    }

    /**
     * Root Type for Tarea
     * <p>
     * 
     * 
     */
    @JsonProperty("informacionTarea")
    public void setInformacionTarea(TareaDTO informacionTarea) {
        this.informacionTarea = informacionTarea;
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
        sb.append(RespuestaDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("estado");
        sb.append('=');
        sb.append(((this.estado == null)?"<null>":this.estado));
        sb.append(',');
        sb.append("informacionTarea");
        sb.append('=');
        sb.append(((this.informacionTarea == null)?"<null>":this.informacionTarea));
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
        result = ((result* 31)+((this.informacionTarea == null)? 0 :this.informacionTarea.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RespuestaDTO) == false) {
            return false;
        }
        RespuestaDTO rhs = ((RespuestaDTO) other);
        return (((((this.estado == rhs.estado)||((this.estado!= null)&&this.estado.equals(rhs.estado)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.tareas == rhs.tareas)||((this.tareas!= null)&&this.tareas.equals(rhs.tareas))))&&((this.informacionTarea == rhs.informacionTarea)||((this.informacionTarea!= null)&&this.informacionTarea.equals(rhs.informacionTarea))));
    }

}
