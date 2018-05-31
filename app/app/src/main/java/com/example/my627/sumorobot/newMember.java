package com.example.my627.sumorobot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class newMember extends Activity implements View.OnClickListener{
    Context context;

    ListTeamObject listTeamObject;


    private TextView nTeamname;
    private EditText newMember;
    private Button addMember;


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu4, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                startActivity(new Intent(this, team_members.class));
                return true;
            case R.id.menu_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        // create an Arraylist from a json string
        Gson gson = new Gson();
        String json = sharedPrefs.getString("LOCLIST", null);
        listTeamObject = gson.fromJson(json, ListTeamObject.class);


              nTeamname = (TextView) findViewById(R.id.teamNameTextView);
              newMember = (EditText) findViewById(R.id.newMemberEditText);
              addMember = (Button) findViewById(R.id.addButton);
              addMember.setOnClickListener(this);



        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        nTeamname.setText(sharedPrefs.getString("Teamname", null));

    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.addButton:
                try {
                    addNewMember(view);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;

        }

    }
    public void addNewMember(View view) throws MalformedURLException {
        try {
            String teamname = nTeamname.getText().toString();
            String nmember = newMember.getText().toString();
            // URL url = new URL("http://192.168.0.13/sumorobot/team.php");
            Globals g = (Globals)getApplication();
            String ipAddress=g.getIpAddress();
            URL url = new URL("http://" + ipAddress + "/sumorobot/addMembers.php");

            DatabaseAccess5 databaseAccess5 = (DatabaseAccess5) new DatabaseAccess5(view.getContext(), url, teamname, nmember).execute();
            url = new URL("http://" + ipAddress + "/sumorobot/list.php");
            DatabaseAccess3 databaseAccess3 = (DatabaseAccess3) new DatabaseAccess3(view.getContext(), url, teamname).execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}
