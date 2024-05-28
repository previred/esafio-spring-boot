package cl.previred.desafio.enums;

public enum StateTaskEnum {
    CREATED("CREATED"),
    WIP("WIP"),
    DONE("DONE");

    private String name;

    StateTaskEnum(String name) {
        this.name = name;
    }

}
