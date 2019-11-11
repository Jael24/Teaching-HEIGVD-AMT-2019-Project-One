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
    public long createCharacter(String charName, long idActor, long idMovie){
        long newId = 0;
        try {
            newId = findMaxId()+1;
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `character`(idchar, idActor, idMovie, charName) VALUES (" + newId + ", " + idActor + ", "+ idMovie + ", '"+ charName + "');");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }


    @Override
    public List<Character> findCharWhereActorHasPlayed(long actorId, long start, long length) {
        List<Character> characters = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `character` INNER JOIN `movie` ON `character`.`idMovie` = `movie`.`idMovie` INNER JOIN `actor` ON `character`.`idActor` = `actor`.`idActor` WHERE `character`.`idActor` = " + actorId + " LIMIT " + start + "," + length +";");
            readResult(characters, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public void updateCharacter(long idCharacter, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `character` SET charName = '" + newName + "' WHERE idChar =" + idCharacter + ";");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCharacter(long idChar){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `character` WHERE idChar =" + idChar + ";");
            ps.executeUpdate();
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

    @Override
    public long findMaxId(){
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(idChar) AS maxId FROM `character`;");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("maxId");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public long getActorIdByCharacter(long idChar){
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT idActor FROM `character` WHERE idChar=" + idChar + ";");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("idActor");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public long countCharacters(long idActor) {
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS countChars FROM `character` WHERE idActor = " + idActor + ";");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("countChars");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public long countAllCharacters() {
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS countChars FROM `character`");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("countChars");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
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
