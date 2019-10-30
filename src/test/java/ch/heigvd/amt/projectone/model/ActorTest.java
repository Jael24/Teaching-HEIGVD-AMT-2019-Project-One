package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

    @Test
    public void itShouldBePossibleToCreateAnActor() {
        Actor anonymousActor = new Actor();
        assertNotNull(anonymousActor);
    }

    @Test
    public void itShouldBePossibleToBuildAnActor() {
        Actor leonardoDiCaprio = new Actor()

        assertEquals("Leonardo", leonardoDiCaprio.getFirstName());
        assertEquals("Di Caprio", leonardoDiCaprio.getLastName());
    }

}