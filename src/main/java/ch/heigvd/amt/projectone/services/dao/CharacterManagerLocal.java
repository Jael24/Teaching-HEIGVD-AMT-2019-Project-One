package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Character;

import java.util.List;

public interface CharacterManagerLocal {
    public List<Character> findAllCharacters();
    void createCharacter(String charName, long idActor, long idMovie);
    void updateCharacter(long idActor, long idMovie, String newName);
    void deleteCharacter(long idActor, long idMovie);
    List<Character> findCharacter(String search);
}
