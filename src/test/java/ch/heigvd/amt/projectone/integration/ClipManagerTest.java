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

    /*
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackages(true, "ch.heigvd");
    }*/


    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAMovie() throws DuplicateKeyException, SQLException {
        Movie titanic = new Movie(1, "Titanic");
        clipManager.createClip(titanic.getTitle());
        assertEquals(1,1);
    }

}
