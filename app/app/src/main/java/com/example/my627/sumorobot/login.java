package com.example.my627.sumorobot;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class login extends Activity implements View.OnClickListener {

private Button login;
        EditText email, password;
        TextView attention;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginButton);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        login.setOnClickListener(this);
        }

@Override
public void onClick(View v) {

        switch (v.getId()) {
        case R.id.loginButton:
        try {
        searchButton(v);
        } catch (MalformedURLException e) {
        e.printStackTrace();
        }
        break;
default:
        }
        }


public void searchButton(View view) throws MalformedURLException {

        try {

        String semail = email.getText().toString();
        String spassword = password.getText().toString();

              // URL url = new URL("http://192.168.0.13/sumorobot/find.php");
            Globals g = (Globals)getApplication();
            String ipAddress=g.getIpAddress();
            URL url = new URL("http://" + ipAddress + "/SumoRobot/find.php");

        DatabaseAccess databaseAccess = (DatabaseAccess) new DatabaseAccess(view.getContext(), url, semail, spassword).execute();
        } catch (MalformedURLException e) {
        e.printStackTrace();
        }

        }

        }
