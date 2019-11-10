package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    public void itShouldBePossibleToCreateAMovie() {
        Movie anonymousMovie = new Movie(0, "test");
        assertNotNull(anonymousMovie);
    }
    @Test
    public void movieShouldBeAbleToHaveInfos() {
        Movie titanic = new Movie(4, "Titanic");
        assertEquals(4, titanic.getIdMovie());
        assertEquals("Titanic", titanic.getTitle());
    }

}