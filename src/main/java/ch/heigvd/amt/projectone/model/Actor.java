package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Actor {
    private long idActor;
    private String fullName;
    private String password;

    public Actor(long idActor, String fullName, String password){
        this.idActor = idActor;
        this.fullName = fullName;
        this.password = password;
    }

    public long getIdActor() {
        return idActor;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }
}
