package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Character;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CharacterManagerLocal {
    public List<Character> findAllCharacters();
}
