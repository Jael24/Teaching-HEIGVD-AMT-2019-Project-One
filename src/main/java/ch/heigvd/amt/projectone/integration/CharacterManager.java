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

/**
 * Class used to represent character's DAOs
 * @author Guillaume Vetter & Jael Dubey
 */
@Stateless
public class CharacterManager implements CharacterManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    private DataSource dataSource;

    /**
     * Method used to find every character that the current user has played.
     * @return the list of every character that the current user has played.
     */
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

    /**
     * Method used to create a character
     * @param charName the name of the character
     * @param idActor the id of the actor
     * @param idMovie the id of the movie
     * @return the id of the new character
     */
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

    /**
     * Method used to find every character played by a given actor
     * @param actorId the actor that we want to retrieve the characters
     * @param start the starting id
     * @param length the length of the result list
     * @return a list of character containing the characters played by the actor, starting at "start" and of length "length"
     */
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

    /**
     * Method used to change the information of a character
     * @param idCharacter the id of the character we want to modify
     * @param newName the new name of the character
     */
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

    /**
     * Method used to delete a given character
     * @param idChar the character ID of the character we want to delete
     */
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

    /**
     * Method used to find a character using a search string
     * @param search the search string
     * @return the list of all characters retrieved.
     */
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

    /**
     * Method used to find the max ID of the character table.
     * @return the max ID of the character table
     */
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

    /**
     * Method used to find an Actor using it's character ID
     * @param idChar the id of the character we want to retrieve
     * @return the Actor that played the given character
     */
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

    /**
     * Method used to count the number of character a given Actor has played
     * @param idActor the given actor
     * @return the number of character that the actor played.
     */
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

    /**
     * Method used to count all characters
     * @return the total number of characters in the table
     */
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
