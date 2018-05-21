package com.example.theopoipoi.exercice_integre;

import android.Manifest;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    SmsManager smsManager = SmsManager.getDefault();
    Button confirm_button;
    EditText edit_temp;
    EditText edit_hum;
    EditText edit_battery;

    DatabaseHelper DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third2);
        DatabaseHelper = new DatabaseHelper(this);
        initViews();
        final String username=getIntent().getStringExtra("name");
        //Get the phone number of the user
        final String number=DatabaseHelper.getNumber(username);
        final Toast toast = Toast.makeText(getApplicationContext(),"everything is OK",Toast.LENGTH_SHORT);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the conditions are respected --> currently, there is a mistake in this conditions
                if (Integer.parseInt(edit_battery.getText().toString())>DatabaseHelper.getinfo(2, username) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(3, username)||
                Integer.parseInt(edit_temp.getText().toString())>DatabaseHelper.getinfo(4, username) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(5, username)||
                        Integer.parseInt(edit_hum.getText().toString())>DatabaseHelper.getinfo(6, username) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(6, username))
                   //Send a message to the user
                    smsManager.sendTextMessage(number ,null, "something isn't ok",null,null);

                else
                    //If everythings are ok
                    toast.show();
        }
        });

    }

    private void initViews() {
        confirm_button = (Button)findViewById(R.id.third_confirm_button);
        edit_battery = (EditText)findViewById(R.id.third_edit_battery);
        edit_temp = (EditText)findViewById(R.id.third_edit_temp);
        edit_hum = (EditText)findViewById(R.id.third_edit_hum);
    }



}



