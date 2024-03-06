package cl.previred.nuevospa.exceptions;

public class ElementoNoEncontradoException extends Exception {
    public ElementoNoEncontradoException(Class<?> entidadClass) {
        super("No se ha encontrado " + entidadClass.getSimpleName());
    }
    public ElementoNoEncontradoException(Class<?> entidadClass, String elementoBuscado) {
        super("No se ha encontrado " + entidadClass.getSimpleName()+" con valor "+elementoBuscado);
    }
}
