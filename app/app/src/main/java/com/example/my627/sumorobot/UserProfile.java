package com.example.my627.sumorobot;

/**
 * Created by my627 on 3/13/2018.
 */

public class UserProfile {
    private String email;
    private String password;

    private String teamlead;
    private String username;

    public UserProfile(String email, String password, String username, String teamlead){
        this.email = email;
        this.password = password;
        this.username = username;
        this.teamlead = teamlead;
    }

    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getTeamlead(){return teamlead;}
    public String getUsername(){return username;}


}
