package com.bignerdranch.android.clientscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by teaman1 on 9/5/2016.
 */
public class login extends AppCompatActivity{
    private AutoCompleteTextView mUsername;
    private EditText mPassword;
    private Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (AutoCompleteTextView)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);
        enter = (Button)findViewById(R.id.sign_in_button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Button Pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(login.this, navigation.class);
                startActivity(i);
            }
        });
    }

}
