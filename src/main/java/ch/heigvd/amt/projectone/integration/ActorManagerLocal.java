package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ActorManagerLocal {
    List<Actor> findAllActors();
    void createActor(String fullname);
    void updateActor(long idActor, String newName);
    void deleteActor(long idActor);
    List<Actor> findActor(String search);
}
