package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Character;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CharacterManagerLocal {
    public List<Character> findAllCharacters();
    long createCharacter(String charName, long idActor, long idMovie);
    void updateCharacter(long idCharacter, String newName);
    void deleteCharacter(long idChar);
    List<Character> findCharacter(String search);
    List<Character> findCharWhereActorHasPlayed(long actorId);
    long getActorIdByCharacter(long idChar);
    long findMaxId();
}
