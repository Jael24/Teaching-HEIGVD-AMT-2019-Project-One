package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Class representing an Actor
 * @author Guillaume Vetter & Jael Dubey
 */
@Getter
@Builder
public class Actor {
    private long idActor;
    private String fullName;
    private String password;

    /**
     * Constructor of an Actor
     * @param idActor the id of the actor
     * @param fullName the name of the actor
     * @param password the password of the actor
     */
    public Actor(long idActor, String fullName, String password){
        this.idActor = idActor;
        this.fullName = fullName;
        this.password = password;
    }

    /**
     * Getter of idActor
     * @return the id of the actor
     */
    public long getIdActor() {
        return idActor;
    }

    /**
     * Getter of the fullname
     * @return the fullname of the actor
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Getter of the password
     * @return the password of the actor
     */
    public String getPassword() {
        return password;
    }
}
