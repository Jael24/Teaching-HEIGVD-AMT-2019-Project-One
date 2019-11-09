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

@Stateless
public class ActorManager implements ActorManagerLocal {
    @Resource(lookup = "jdbc/cinema")
    DataSource dataSource;

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
