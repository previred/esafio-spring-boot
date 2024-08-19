package cl.corellana.taskmanager.api.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public enum TaskStatus {
    TO_DO(1) ,
    IN_PROGRESS(2),
    DONE(3);

    private final Integer id;

    public Integer getId() {
        return id;
    }
    TaskStatus(int id) {
        this.id = id;
    }

    public static TaskStatus byId(Integer id){
        return Arrays.stream(TaskStatus.values()).filter(ts -> Objects.equals(ts.id, id)).findFirst().orElse(null);
    }
}
