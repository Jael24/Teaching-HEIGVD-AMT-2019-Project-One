package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Actor {
    private long idActor;
    private String fullName;

    public Actor(long idActor, String fullName){
        this.idActor = idActor;
        this.fullName = fullName;

    }
}
