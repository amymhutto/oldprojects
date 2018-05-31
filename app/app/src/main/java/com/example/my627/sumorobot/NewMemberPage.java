package com.example.my627.sumorobot;

/**
 * Created by my627 on 3/13/2018.
 */

public class NewMemberPage {
    private String newMemberName;
    private String teamname;

    public NewMemberPage(String teamname, String newMemberName){
        this.teamname = teamname;
        this.newMemberName = newMemberName;
    }

    public String getTeamname(){return teamname;}
    public String getNewMemberName(){return newMemberName;}

}
