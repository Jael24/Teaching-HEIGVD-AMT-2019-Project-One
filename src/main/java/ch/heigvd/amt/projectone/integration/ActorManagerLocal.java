package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface of the Actor's DAO
 * @author Guillaume Vetter & Jael Dubey
 */
@Local
public interface ActorManagerLocal {
    List<Actor> findAllActors();
    Actor createActor(String fullname, String password);
    void updateActor(long idActor, String newName);
    void deleteActor(long idActor);
    List<Actor> findActor(String search);
    Actor findActorByID(long search);
    public long findMaxId();
}
