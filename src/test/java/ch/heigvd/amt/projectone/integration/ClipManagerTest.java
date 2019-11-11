package ch.heigvd.amt.projectone.integration;

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

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;

import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ClipManagerTest {

    @EJB
    ClipManagerLocal clipManager;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAMovie() {
        Movie titanic = new Movie(1, "Titanic");
        Movie m = clipManager.createClip(titanic.getTitle());
        Movie movie = clipManager.findClipById(m.getIdMovie());
        assertEquals(titanic.getTitle(), movie.getTitle());
    }

    @Test
    public void itShouldBePossibleToDeleteAMovie() {
        Movie cheerleader = null;
        Movie movie = clipManager.createClip("#1 Cheerleader Camp");

        assertNotNull(movie);

        clipManager.deleteClip(movie.getIdMovie());
        cheerleader = clipManager.findClipById(movie.getIdMovie());

        assertNull(cheerleader);
    }

    @Test
    public void itShouldBePossibleToUpdateAMovie() {
        Movie cheerleader = Movie.builder().title("error").build();
        Movie m = clipManager.createClip(cheerleader.getTitle());

        assertEquals(m.getTitle(), cheerleader.getTitle());

        clipManager.updateClip(m.getIdMovie(), "#1 Cheerleader Camp");
        cheerleader = clipManager.findClipById(m.getIdMovie());

        assertEquals("#1 Cheerleader Camp", cheerleader.getTitle());

    }
}
