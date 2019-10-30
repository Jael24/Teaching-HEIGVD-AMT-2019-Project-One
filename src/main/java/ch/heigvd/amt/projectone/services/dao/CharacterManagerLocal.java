package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Character;

import java.util.List;

public interface CharacterManagerLocal {
    public List<Character> findAllCharacters();
}
