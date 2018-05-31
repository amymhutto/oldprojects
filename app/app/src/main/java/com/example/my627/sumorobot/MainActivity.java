package com.example.my627.sumorobot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener {
    Context context;

    private AlertDialog.Builder build;
    UserProfile userProfile;

    private TextView username;
    private TextView email;
    private TextView teamName, address, city,state, zip;


    TextView txTeamname;
    TextView txAddress;
    TextView txCity;
    TextView txState;
    TextView txZip;

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                startActivity(new Intent(this, login.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = (TextView) findViewById(R.id.usernameTextView);
        email = (TextView) findViewById(R.id.emailTextView);

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        // create an Arraylist from a json string
        Gson gson = new Gson();
        String json = sharedPrefs.getString("LOCLIST", null);
        userProfile = gson.fromJson(json, UserProfile.class);

        email.setText(userProfile.getEmail());
        username.setText(userProfile.getUsername());

        Button teamsButton= (Button) findViewById(R.id.teamsButton);
        teamsButton.setOnClickListener(this);
        Button createTeamButton = (Button) findViewById(R.id.createTeamButton);
        createTeamButton.setOnClickListener(this);


    }




    @Override
    public void onClick(View view) {
       switch (view.getId()) {

            case R.id.teamsButton:
                try {
                    displayTeams(view);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
           case R.id.createTeamButton:
               startActivity(new Intent(this, AddTeam.class));

               break;

            default:
                break;

        }

    }

    public void displayTeams(View view) throws MalformedURLException {
       try {
           // URL url = new URL("http://192.168.0.13/sumorobot/team.php");
           Globals g = (Globals)getApplication();
           String ipAddress=g.getIpAddress();
           URL url = new URL("http://" + ipAddress + "/sumorobot/team.php");

            DatabaseAccess2 databaseAccess2 = (DatabaseAccess2) new DatabaseAccess2(view.getContext(), url, userProfile.getEmail()).execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

}
