
package cl.previred.taskmanagement.application.dto.response;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Root Type for Tarea
 * <p>
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nombre",
        "descripcion",
        "usuario",
        "estado",
        "codigo",
        "fechaAsignacion",
        "fechaCreacion",
        "fechaTermino",
        "tablero"
})
@Generated("jsonschema2pojo")
public class TareaDTO {

    @JsonProperty("nombre")
    private String nombre;
    /**
     *
     */
    @JsonProperty("descripcion")
    @JsonPropertyDescription("")
    private String descripcion;
    @JsonProperty("usuario")
    private String usuario;
    /**
     *
     */
    @JsonProperty("estado")
    @JsonPropertyDescription("")
    private TareaDTO.Estado estado;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("codigo")
    @JsonPropertyDescription("")
    private Long codigo;
    /**
     *
     */
    @JsonProperty("fechaAsignacion")
    @JsonPropertyDescription("")
    private Date fechaAsignacion;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("fechaCreacion")
    @JsonPropertyDescription("")
    private Date fechaCreacion;
    /**
     *
     */
    @JsonProperty("fechaTermino")
    @JsonPropertyDescription("")
    private Date fechaTermino;
    /**
     * Root Type for TableroDTO
     * <p>
     *
     * (Required)
     *
     */
    @JsonProperty("tablero")
    @JsonPropertyDescription("")
    private TableroDTO tablero;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     */
    @JsonProperty("descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    /**
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
     */
    @JsonProperty("estado")
    public TareaDTO.Estado getEstado() {
        return estado;
    }

    /**
     *
     */
    @JsonProperty("estado")
    public void setEstado(TareaDTO.Estado estado) {
        this.estado = estado;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("codigo")
    public Long getCodigo() {
        return codigo;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("codigo")
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     *
     */
    @JsonProperty("fechaAsignacion")
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     *
     */
    @JsonProperty("fechaAsignacion")
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("fechaCreacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("fechaCreacion")
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     *
     */
    @JsonProperty("fechaTermino")
    public Date getFechaTermino() {
        return fechaTermino;
    }

    /**
     *
     */
    @JsonProperty("fechaTermino")
    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    /**
     * Root Type for TableroDTO
     * <p>
     *
     * (Required)
     *
     */
    @JsonProperty("tablero")
    public TableroDTO getTablero() {
        return tablero;
    }

    /**
     * Root Type for TableroDTO
     * <p>
     *
     * (Required)
     *
     */
    @JsonProperty("tablero")
    public void setTablero(TableroDTO tablero) {
        this.tablero = tablero;
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
        sb.append(TareaDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("estado");
        sb.append('=');
        sb.append(((this.estado == null)?"<null>":this.estado));
        sb.append(',');
        sb.append("codigo");
        sb.append('=');
        sb.append(((this.codigo == null)?"<null>":this.codigo));
        sb.append(',');
        sb.append("fechaAsignacion");
        sb.append('=');
        sb.append(((this.fechaAsignacion == null)?"<null>":this.fechaAsignacion));
        sb.append(',');
        sb.append("fechaCreacion");
        sb.append('=');
        sb.append(((this.fechaCreacion == null)?"<null>":this.fechaCreacion));
        sb.append(',');
        sb.append("fechaTermino");
        sb.append('=');
        sb.append(((this.fechaTermino == null)?"<null>":this.fechaTermino));
        sb.append(',');
        sb.append("tablero");
        sb.append('=');
        sb.append(((this.tablero == null)?"<null>":this.tablero));
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
        result = ((result* 31)+((this.estado == null)? 0 :this.estado.hashCode()));
        result = ((result* 31)+((this.codigo == null)? 0 :this.codigo.hashCode()));
        result = ((result* 31)+((this.fechaAsignacion == null)? 0 :this.fechaAsignacion.hashCode()));
        result = ((result* 31)+((this.fechaTermino == null)? 0 :this.fechaTermino.hashCode()));
        result = ((result* 31)+((this.fechaCreacion == null)? 0 :this.fechaCreacion.hashCode()));
        result = ((result* 31)+((this.usuario == null)? 0 :this.usuario.hashCode()));
        result = ((result* 31)+((this.tablero == null)? 0 :this.tablero.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nombre == null)? 0 :this.nombre.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TareaDTO) == false) {
            return false;
        }
        TareaDTO rhs = ((TareaDTO) other);
        return (((((((((((this.descripcion == rhs.descripcion)||((this.descripcion!= null)&&this.descripcion.equals(rhs.descripcion)))&&((this.estado == rhs.estado)||((this.estado!= null)&&this.estado.equals(rhs.estado))))&&((this.codigo == rhs.codigo)||((this.codigo!= null)&&this.codigo.equals(rhs.codigo))))&&((this.fechaAsignacion == rhs.fechaAsignacion)||((this.fechaAsignacion!= null)&&this.fechaAsignacion.equals(rhs.fechaAsignacion))))&&((this.fechaTermino == rhs.fechaTermino)||((this.fechaTermino!= null)&&this.fechaTermino.equals(rhs.fechaTermino))))&&((this.fechaCreacion == rhs.fechaCreacion)||((this.fechaCreacion!= null)&&this.fechaCreacion.equals(rhs.fechaCreacion))))&&((this.usuario == rhs.usuario)||((this.usuario!= null)&&this.usuario.equals(rhs.usuario))))&&((this.tablero == rhs.tablero)||((this.tablero!= null)&&this.tablero.equals(rhs.tablero))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nombre == rhs.nombre)||((this.nombre!= null)&&this.nombre.equals(rhs.nombre))));
    }


    /**
     *
     */
    @Generated("jsonschema2pojo")
    public enum Estado {

        TO_DO("TO_DO"),
        IN_PROGRESS("IN_PROGRESS"),
        QA("QA"),
        DONE("DONE"),
        PRODUCTION("PRODUCTION");
        private final String value;
        private final static Map<String, TareaDTO.Estado> CONSTANTS = new HashMap<String, TareaDTO.Estado>();

        static {
            for (TareaDTO.Estado c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Estado(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static TareaDTO.Estado fromValue(String value) {
            TareaDTO.Estado constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
