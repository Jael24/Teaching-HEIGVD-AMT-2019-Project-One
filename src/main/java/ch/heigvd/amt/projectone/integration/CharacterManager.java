package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Actor;
import ch.heigvd.amt.projectone.model.Character;
import ch.heigvd.amt.projectone.model.Movie;

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
public class CharacterManager implements CharacterManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    private DataSource dataSource;

    @Override
    public List<Character> findAllCharacters(){
        List<Character> chars = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `character` INNER JOIN `movie` ON `character`.`idMovie` = `movie`.`idMovie` INNER JOIN `actor` ON `character`.`idActor` = `actor`.`idActor`");
            readResult(chars, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

    @Override
    public void createCharacter(String charName, long idActor, long idMovie){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `character`(idActor, idMovie, charName) VALUES (" + idActor + ", "+ idMovie + ", "+ charName + ");");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Character> findCharWhereActorHasPlayed(long actorId) {
        List<Character> characters = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `character` INNER JOIN `movie` ON `character`.`idMovie` = `movie`.`idMovie` INNER JOIN `actor` ON `character`.`idActor` = `actor`.`idActor` WHERE `character`.`idActor` = " + actorId + ";");
            readResult(characters, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public void updateCharacter(long idActor, long idMovie, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `character` SET charName = '" + newName + "' WHERE idActor =" + idActor + "AND idMovie =" + idMovie + ";");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCharacter(long idActor, long idMovie){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `character` WHERE idActor =" + idActor + "AND idMovie =" + idMovie + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Character> findCharacter(String search){
        List<Character> chars = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `character` INNER JOIN `movie` ON `character`.`idMovie` = `movie`.`idMovie` INNER JOIN `actor` ON `character`.`idActor` = `actor`.`idActor` WHERE charName LIKE '%" + search + "%';");
            readResult(chars, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

    private void readResult(List<Character> chars, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            long idChar = result.getLong("idChar");
            Movie movie = new Movie(result.getLong("idMovie"), result.getString("title"));
            Actor actor = new Actor(result.getLong("idActor"), result.getString("fullname"), result.getString("password"));
            String charName = result.getString("charName");
            chars.add(new Character(idChar, actor, movie, charName));
        }
    }

}
