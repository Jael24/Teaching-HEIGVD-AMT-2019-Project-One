package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Actor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ActorManager implements ActorManagerLocal {
    @Resource(lookup = "jdbc/cinema")
            private DataSource dataSource;

    public List<Actor> findAllActors(){
        List<Actor> actors = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM actor;");
            readResult(actors, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public void createActor(String fullname){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO actor(fullName) VALUES (" + fullname + ");");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateActor(long idActor, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE actor SET fullName = '" + newName + "' WHERE idActor =" + idActor + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteActor(long idActor){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM actor WHERE idActor =" + idActor + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Actor> findActor(String search){
        List<Actor> actors = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM actor WHERE fullName LIKE '" + search + "';");
            readResult(actors, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    private void readResult(List<Actor> actors, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            String fullName = result.getString("fullName");
            long pid = result.getLong("idActor");
            actors.add(new Actor(pid, fullName));
        }
    }
}
