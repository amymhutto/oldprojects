package com.example.my627.sumorobot;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ListTeams extends Activity{
    Context context;
    private AlertDialog.Builder build;

    private TextView teamname, address, city,state, zip;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_teams);
        final ListView mListView = (ListView) findViewById(R.id.teamsListView);

        teamname = (TextView) findViewById(R.id.teamnameTextView);
        address = (TextView) findViewById(R.id.addressTextView);
        city = (TextView) findViewById(R.id.cityTextView);
        state = (TextView) findViewById(R.id.stateTextView);
        zip = (TextView) findViewById(R.id.zipTextView);

        Log.d(TAG, "onCreate: Started.");

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();
        String json = sharedPrefs.getString("TEAMS", null);
        ArrayList<DataObject> al = new ArrayList<DataObject>();
        al = gson.fromJson(json, new TypeToken<ArrayList<DataObject>>() {
        }.getType());


        TeamsListAdapter adapter = new TeamsListAdapter(this, R.layout.activity_listinfo, al);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Intent intent = new Intent(ListTeams.this, DataObject.class);
                //startActivity(intent);

                DataObject dataObject = (DataObject) mListView.getItemAtPosition(i);
                // the id will be used to get the record from the database
                String teamname = dataObject.getTeamname();

                try {
                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor e = sharedPrefs.edit();
                    e.putString("Teamname", teamname);
                    e.commit();
                  //URL url = new URL("http://192.168.0.13/SumoRobot/list.php");
                    Globals g = (Globals)getApplication();
                    String ipAddress=g.getIpAddress();
                    URL url = new URL("http://" + ipAddress + "/sumorobot/list.php");

                    DatabaseAccess3 databaseAccess3 = (DatabaseAccess3) new DatabaseAccess3(view.getContext(), url, teamname).execute();

                    
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });




    }




}
