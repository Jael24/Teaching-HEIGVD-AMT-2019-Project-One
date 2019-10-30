package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
public class Actor {

    private long idActor;
    private String fullname;

    public Actor(long idActor, String fullname){
        this.idActor = idActor;
        this.fullname = fullname;
    }
}
