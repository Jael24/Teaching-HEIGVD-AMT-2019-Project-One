package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    @Test
    public void itShouldBePossibleToCreateAnActor() {
        Actor anonymousActor = new Actor(0, "test", "password");
        assertNotNull(anonymousActor);
    }
    @Test
    public void actorShouldBeAbleToHaveInfos() {
        Actor diCaprio = new Actor(2, "Leonardo Di Caprio", "password");
        assertEquals(2, diCaprio.getIdActor());
        assertEquals("Leonardo Di Caprio", diCaprio.getFullName());
        assertEquals("password", diCaprio.getPassword());
    }

} 