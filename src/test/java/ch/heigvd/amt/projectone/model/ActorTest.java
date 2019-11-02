package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

    @Test
    public void itShouldBePossibleToCreateAnActor() {
        Actor anonymousActor = new Actor(0, "test");
        assertNotNull(anonymousActor);
    }

    @Test
    public void actorShouldBeAbleToHaveAName() {
        Actor diCaprio = new Actor(0, "Leonardo Di Caprio");
        assertEquals("Leonardo Di Caprio", diCaprio.getFullName());
    }

}