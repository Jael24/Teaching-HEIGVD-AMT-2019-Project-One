package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Actor {
    private long idActor;
    private String fullName;
    private String password;

    public Actor(long idActor, String fullName, String password){
        this.idActor = idActor;
        this.fullName = fullName;
        this.password = password;
    }
}
