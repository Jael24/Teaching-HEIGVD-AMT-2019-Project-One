package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Actor {
    private long idActor;
    private String fullname;

    public Actor(long idActor, String fullname){
        this.idActor = idActor;
        this.fullname = fullname;

    }
}
