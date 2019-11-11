package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Actor;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to manage every Actor's DAOs.
 * @author Guillaume Vetter & Jael Dubey
 */
@Stateless
public class ActorManager implements ActorManagerLocal {
    @Resource(lookup = "jdbc/cinema")
    DataSource dataSource;

    /**
     * Method used to find the first 100 actors.
     * @return the list of the first 100 actors in the database
     */
    @Override
    public List<Actor> findAllActors(){
        List<Actor> actors = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM actor LIMIT 100;");
            readResult(actors, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    /**
     * Method used to create an Actor, given the name and the password
     * @param fullname the name of the actor
     * @param password the password for the account
     * @return the newly created actor
     */
    @Override
    public Actor createActor(String fullname, String password){
        long newId = 0;
        try {
            newId = findMaxId()+1;
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO actor(idActor, fullname, password) VALUES (" + newId + ", '" + fullname + "', '" + password + "');");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Actor(newId, fullname, password);
    }

    /**
     * Method used to update an actor's information
     * @param idActor the id of the actor
     * @param newName the new name of the actor
     */
    @Override
    public void updateActor(long idActor, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE actor SET fullName = '" + newName + "' WHERE idActor =" + idActor + ";");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to delete an actor, given it's ID
     * @param idActor the ID of the actor we want to delete.
     */
    @Override
    public void deleteActor(long idActor){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM actor WHERE idActor =" + idActor + ";");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to find an actor, given a string search.
     * @param search the name of the actor
     * @return a list of actors which name contains the search string.
     */
    @Override
    public List<Actor> findActor(String search){
        List<Actor> actors = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM actor WHERE fullName LIKE '%" + search + "%';");
            readResult(actors, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    /**
     * Method used to find an actor given it's ID
     * @param search the ID of the actor we want to find
     * @return null if the actor is not found, the found actor otherwise.
     */
    @Override
    public Actor findActorByID(long search){
        Actor actor = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM actor WHERE idActor = " + search + ";");
            ResultSet result = ps.executeQuery();
            result.next();
            actor = new Actor(result.getLong("idActor"), result.getString("fullname"), result.getString("password"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    /**
     * Method used to find the maximum ID in the database
     * @return the maximum ID of the database.
     */
    @Override
    public long findMaxId(){
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(idActor) AS maxId FROM actor;");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("maxId");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * Helper method used to read the result of a query and to put it in a list.
     * @param actors the list of actors in which we put the actors requested in the query
     * @param ps the PreparedStatement on which we execute the query
     * @throws SQLException
     */
    private void readResult(List<Actor> actors, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            String fullName = result.getString("fullname");
            long pid = result.getLong("idActor");
            String password = result.getString("password");
            actors.add(new Actor(pid, fullName, password));
        }
    }
}
