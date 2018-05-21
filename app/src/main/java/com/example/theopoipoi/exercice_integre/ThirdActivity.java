package com.example.theopoipoi.exercice_integre;

import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
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
        String username=getIntent().getStringExtra("name");
        final String number=DatabaseHelper.getNumber(username);
        final Toast toast = Toast.makeText(getApplicationContext(),"everything is OK",Toast.LENGTH_SHORT);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(edit_battery.getText().toString())>DatabaseHelper.getinfo(2) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(3)||
                Integer.parseInt(edit_temp.getText().toString())>DatabaseHelper.getinfo(4) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(5 )||
                        Integer.parseInt(edit_hum.getText().toString())>DatabaseHelper.getinfo(6) || Integer.parseInt(edit_battery.getText().toString())<DatabaseHelper.getinfo(6))
                   smsManager.sendTextMessage(number ,null, "something isn't ok",null,null);

                else
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
