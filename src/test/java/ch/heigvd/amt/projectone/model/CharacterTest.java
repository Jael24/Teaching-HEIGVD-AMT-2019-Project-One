package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    public void itShouldBePossibleToCreateACharacter() {
        Character anonymousCharacter = new Character("test", 12, 42);
        assertNotNull(anonymousCharacter);
    }

    @Test
    public void characterShouldBeAbleToHaveAName() {
        Character harryPotter = new Character("Harry Potter", 1,2);
        assertEquals("Harry Potter", harryPotter.getName());
    }


}