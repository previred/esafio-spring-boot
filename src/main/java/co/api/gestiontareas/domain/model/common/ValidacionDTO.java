package co.api.gestiontareas.domain.model.common;


public record ValidacionDTO(
        String campo,
        String error
) {
}