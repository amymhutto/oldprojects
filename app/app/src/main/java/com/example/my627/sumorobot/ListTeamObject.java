package com.example.my627.sumorobot;


public class ListTeamObject{
    private String teamname;
    private String membername;

    public ListTeamObject(String teamname, String membername) {


        this.teamname = teamname;
        this.membername= membername;
    }

    public String getTeamname() {return teamname;}
    public String getMembername() {return membername;}
}
