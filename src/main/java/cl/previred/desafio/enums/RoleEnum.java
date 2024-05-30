package cl.previred.desafio.enums;

public enum RoleEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
