package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    public void itShouldBePossibleToCreateACharacter() {
        Character anonymousCharacter = new Character(12, 42, "test");
        assertNotNull(anonymousCharacter);
    }

    @Test
    public void characterShouldBeAbleToHaveAName() {
        Character harryPotter = new Character(2, 1,"Harry Potter");
        assertEquals("Harry Potter", harryPotter.getName());
    }


}