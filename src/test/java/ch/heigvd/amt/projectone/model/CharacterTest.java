package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    // TODO mock actor and movie
    @Test
    public void itShouldBePossibleToCreateACharacter() {
        Character anonymousCharacter = new Character(0, new Actor(0,"",""), new Movie(0,""), "charName");
        assertNotNull(anonymousCharacter);
    }
    @Test
    public void characterShouldBeAbleToHaveInfos() {
        Character harryPotter = new Character(2, new Actor(1,"Daniel Radcliffe", "123456"), new Movie(0, "Harry Potter"), "Harry Potter");
        assertEquals(2, harryPotter.getIdChar());
        assertEquals("Harry Potter", harryPotter.getCharName());
    }
}