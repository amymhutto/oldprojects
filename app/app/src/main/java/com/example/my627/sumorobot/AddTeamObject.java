package com.example.my627.sumorobot;

public class AddTeamObject {
    private String nTeamname;
    private String nAddress;
    private String nCity;
    private String nSate;
    private String nZip;

    public AddTeamObject(String nTeamname, String nAddress, String nCity, String nState, String nZip) {


        this.nTeamname = nTeamname;
        this.nAddress= nAddress;
        this.nCity = nCity;
        this.nSate = nState;
        this.nZip = nZip;
    }

    public String getnTeamname() {return nTeamname;}
    public String getnAddress() {return nAddress;}
    public String getnCity() {
        return nCity;
    }
    public String getnState() {return nSate;}
    public String getnZip() {
        return nZip;
    }
}
