package com.example.my627.sumorobot;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by stu2000 on 2/6/2018.
 */

public class DatabaseAccess extends AsyncTask<Void, Void, String> {

    Context context;
    URL url;
    String email, password;
     //String username, teamName, address, city, state;

    String file;
    TextView teamName;
    TextView address;
    TextView city;
    TextView state;
    TextView zip;

    HttpURLConnection conn;
    String anydata = "yes";

    ArrayList<UserProfile> al;   // used for a fetchall seneario
    UserProfile userProfile;

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    public DatabaseAccess(Context ctx, URL url, String semail, String spassword) {
        this.context = ctx;
        this.url = url;
        this.email = semail;
        this.password = spassword;


    }

    public DatabaseAccess(Context context, URL url, TextView sTeamName, TextView sAddress, TextView sCity, TextView sState, TextView sZip, String file) {
        this.context = context;
        this.url = url;
        this.teamName = sTeamName;
        this.address = sAddress;
        this.city = sCity;
        this.state = sState;
        this.zip = sZip;
    }


    // public DatabaseAccess(Context context, URL url, String susername) {
  //  }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(Void... params) {

        try {
            // Setup HttpURLConnection class to send and receive data from php and mysql
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");

            // setDoInput and setDoOutput method depict handling of both send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // This passes parameters to the PHP file in the server
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("inputEmail", email)
                    .appendQueryParameter("inputPassword", password);
            String query = builder.build().getEncodedQuery();

            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "exception";
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String json = reader.readLine();


                // used a try catch so in case no data from reader.readLine  making the string json null which would cause the
                // application to bomb. By using this try catch the bomb is prevented. The anydata is then set to no
                try {
                     if (json.length() != 0) {
                        // following will take the json string and pass it thru gson identifing data has DataObject in it.
                        // this will create an arraylist of the data that can now get passed into the ListAdapter in file ListCustomers
                        Gson gson = new Gson();
                  /*      al = new ArrayList<UserProfile>();
                        al = gson.fromJson(json, new TypeToken<ArrayList<UserProfile>>() {
                        }.getType());*/
                        userProfile = gson.fromJson(json, UserProfile.class);
                    }
                } catch (Exception e) {
                    //   e.printStackTrace();
                    anydata = "no";;
                }



            } else {

                return ("unsuccessful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            conn.disconnect();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        if (anydata == "yes") {
            // follow will store the arraylist created to a SharedPreference To be brought up in the ListView Screen ListCustomers
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor e = sharedPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(userProfile);
            e.putString("LOCLIST", json);
            e.commit();
            // Following will launch the ListCustomers screen
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "User Not found", Toast.LENGTH_LONG).show();
        }
    }


}
