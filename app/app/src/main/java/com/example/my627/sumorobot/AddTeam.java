package com.example.my627.sumorobot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.net.MalformedURLException;
import java.net.URL;

public class AddTeam extends Activity implements View.OnClickListener{

    Context context;
    AddTeamObject addTeamObject;
    UserProfile userProfile;



    private EditText vemail, newteamname, newaddress, newcity,newstate, newzip;
    private Button submitButton;

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        vemail = (EditText) findViewById(R.id.verifyEmailEditText);
        newteamname = (EditText) findViewById(R.id.newTeamEditText);
        newaddress = (EditText) findViewById(R.id.newAddressEditText);
        newcity = (EditText) findViewById(R.id.newCityEditText);
        newstate = (EditText) findViewById(R.id.newStateEditText);
        newzip = (EditText) findViewById(R.id.newZipEditText);

        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(this);
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.submitButton:
                try {
                    searchButton(view);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }


    public void searchButton(View view) throws MalformedURLException {

        try {
            String email = vemail.getText().toString();
            String nTeamname = newteamname.getText().toString();
            String nAddress = newaddress.getText().toString();
            String nCity = newcity.getText().toString();
            String nState = newstate.getText().toString();
            String nZip = newzip.getText().toString();

            Globals g = (Globals)getApplication();
            String ipAddress=g.getIpAddress();
            URL url = new URL("http://" + ipAddress + "/SumoRobot/addTeam.php");

            DatabaseAccess6 databaseAccess6 = (DatabaseAccess6) new DatabaseAccess6(view.getContext(), url, email, nTeamname,  nAddress,  nCity,  nState,  nZip).execute();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }


}
