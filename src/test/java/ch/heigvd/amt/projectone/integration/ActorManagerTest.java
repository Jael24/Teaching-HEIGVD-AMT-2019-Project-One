package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Actor;
import ch.heigvd.amt.projectone.model.Movie;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ActorManagerTest {

    @EJB
    ActorManagerLocal actorManager;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAnActor() {
        Actor dukeErica = new Actor(1, "Erica Duke", "password");
        Actor a = actorManager.createActor(dukeErica.getFullName(), dukeErica.getPassword());
        Actor actor = actorManager.findActorByID(a.getIdActor());
        assertEquals(dukeErica.getFullName(), actor.getFullName());
    }

    @Test
    public void itShouldBePossibleToDeleteAnActor() {
        Actor dukeErica = null;
        Actor actor = actorManager.createActor("Erica Duke", "password");

        assertNotNull(actor);

        actorManager.deleteActor(actor.getIdActor());
        dukeErica = actorManager.findActorByID(actor.getIdActor());

        assertNull(dukeErica);
    }

    @Test
    public void itShouldBePossibleToUpdateAnActor() {
        Actor dukeErica = Actor.builder().fullName("error").build();
        Actor a = actorManager.createActor(dukeErica.getFullName(), dukeErica.getPassword());

        assertEquals(a.getFullName(), dukeErica.getFullName());

        actorManager.updateActor(a.getIdActor(), "Erica Duke");
        dukeErica = actorManager.findActorByID(a.getIdActor());

        assertEquals("Erica Duke", dukeErica.getFullName());

    }

}
