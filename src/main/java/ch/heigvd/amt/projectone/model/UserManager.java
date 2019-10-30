package ch.heigvd.amt.projectone.model;

import java.util.HashMap;

public class UserManager {
    private static UserManager userManager;
    private HashMap<String,String> users;

    private UserManager(){
        users = new HashMap<String, String>();
    }

    public UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    public void registerUser(String username, String password){
        users.put(username, password);
    }

    public boolean checkPassword(String username, String password) throws IllegalArgumentException{
        String pass = users.get(username);
        if(pass != null){
            if(password.equals(pass)){
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("User does not exist.");
    }
}
