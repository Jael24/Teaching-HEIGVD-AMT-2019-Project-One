package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ActorManagerLocal {
    public List<Actor> findAllActors();
    public void createActor(String fullname);
    public void updateActor(long idActor, String newName);
    public void deleteActor(long idActor);
    public List<Actor> findActor(String search);
}
