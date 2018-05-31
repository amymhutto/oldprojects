package com.example.my627.sumorobot;

/**
 * Created by my627 on 3/18/2018.
 */

public class DataObject {
    private String teamname;
    private String address;
    private String city;
    private String state;
    private String zip;

    public DataObject(String teamname, String address, String city, String state, String zip) {


        this.teamname = teamname;
        this.address= address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getTeamname() {return teamname;}
    public String getAddress() {return address;}
    public String getCity() {
        return city;
    }
    public String getState() {return state;}
    public String getZip() {
        return zip;
    }
}
