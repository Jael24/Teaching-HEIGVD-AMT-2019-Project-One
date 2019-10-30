package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClipTest {

    @Test
    public void itShouldBePossibleToCreateAClip() {
        Clip anonymousClip = new Clip(0, "test");
        assertNotNull(anonymousClip);
    }

    @Test
    public void clipShouldBeAbleToHaveATitle() {
        Clip theLionKing = new Clip(5, "The Lion King");
        assertEquals("The Lion King", theLionKing.getTitle());
    }


}