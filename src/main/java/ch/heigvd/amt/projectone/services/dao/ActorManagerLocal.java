package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Actor;

import java.util.List;

public interface ActorManagerLocal {
    List<Actor> findAllActors();
    void createActor(String fullname);
    void updateActor(long idActor, String newName);
    void deleteActor(long idActor);
    List<Actor> findActor(String search);
}
