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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by my627 on 3/5/2018.
 */

public class team_members extends Activity {

    private TextView sTeamName;
    private TextView membername;
    private EditText memberEditText;
    Context context;
    private AlertDialog.Builder build;

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(this, newMember.class));
                return true;
            case R.id.menu_back:
                startActivity(new Intent(this, ListTeams.class));
                return true;
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
        setContentView(R.layout.activity_team_members);
        final ListView mListView = (ListView) findViewById(R.id.teamMembersListView);

        sTeamName = (TextView) findViewById(R.id.teamnameTextView);
        membername = (TextView) findViewById(R.id.teammember);

        Log.d(TAG, "onCreate: Started.");

        context = getApplicationContext();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();
        String json = sharedPrefs.getString("MEMBERS", null);
        ArrayList<ListTeamObject> al = new ArrayList<ListTeamObject>();
        al = gson.fromJson(json, new TypeToken<ArrayList<ListTeamObject>>() {
        }.getType());



        if(!json.equals("")) {
            MembersListAdapter adapter = new MembersListAdapter(this, R.layout.activity_listmembers, al);

            mListView.setAdapter(adapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                ListTeamObject listTeamObject = (ListTeamObject) mListView.getItemAtPosition(i);
                // the id will be used to get the record from the database
                String teammember = listTeamObject.getMembername();

            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> listView, View view,
                                           final int position, long id) {
                final View v = view;

                ListTeamObject incidentObject = (ListTeamObject) mListView.getItemAtPosition(position);

                final String rMembername = incidentObject.getMembername();

                build = new AlertDialog.Builder(team_members.this);
                build.setTitle("Deleting " + rMembername + " ");
                build.setMessage("Do you want to delete ?");
                build.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // if yes was chosen then delete member

                                try {
                                    deleteMember(v, rMembername);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                // return to team members screen
                                context = getApplicationContext();
                                startActivity(new Intent(context, team_members.class));
                                Toast.makeText(
                                        getApplicationContext(),
                                        rMembername + " "
                                                + " was deleted.", Toast.LENGTH_LONG).show();

                            }
                        });

                build.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = build.create();
                alert.show();

                return true;
            }


        });

    }

    private void deleteMember(View view, String rMembername) throws MalformedURLException {

        try {

            String teamname = sTeamName.getText().toString();
           // URL url = new URL("http://192.168.0.13/SumoRobot/updateMembers.php");
            Globals g = (Globals)getApplication();
            String ipAddress=g.getIpAddress();
            URL url = new URL("http://" + ipAddress + "/sumorobot/updateMembers.php");
           DatabaseAccess4 databaseAccess4 = (DatabaseAccess4) new DatabaseAccess4(view.getContext(), url, rMembername).execute();
            url = new URL("http://" + ipAddress + "/sumorobot/list.php");
            DatabaseAccess3 databaseAccess3 = (DatabaseAccess3) new DatabaseAccess3(view.getContext(), url, teamname).execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}

