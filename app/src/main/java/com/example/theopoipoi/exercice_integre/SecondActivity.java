package com.example.theopoipoi.exercice_integre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{

    Button confirm_button;
    EditText edit_min_temp;
    EditText edit_max_temp;
    EditText edit_min_hum;
    EditText edit_max_hum;
    EditText edit_min_battery;
    EditText edit_max_battery;
    EditText edit_phone;


    User currentUser = new User();

    DatabaseHelper DatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = (TextView) findViewById(R.id.view_username);
        //We display the text "hello" with the name of the user sent from the previous activity
        String Hello = "Hello "+getIntent().getStringExtra("name")+"!";
        textView.setText(Hello);
        initViews();
        DatabaseHelper = new DatabaseHelper(this);

        //Listener on the Confirm button
        confirm_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //We recover the text in the edittext and we convert it into an integer
                int battery_min  = Integer.parseInt(edit_min_battery.getText().toString());
                int battery_max  = Integer.parseInt(edit_max_battery.getText().toString());
                int temperature_min  = Integer.parseInt(edit_min_temp.getText().toString());
                int temperature_max  = Integer.parseInt(edit_max_temp.getText().toString());
                int humidity_min  = Integer.parseInt(edit_min_hum.getText().toString());
                int humidity_max  = Integer.parseInt(edit_max_hum.getText().toString());
                String phone  = edit_phone.getText().toString();

                //We attribute some values to the user
                currentUser.setUsername(getIntent().getStringExtra("name"));
                currentUser.setBattery_max(battery_max);
                currentUser.setBattery_min(battery_min);
                currentUser.setHumidity_max(humidity_max);
                currentUser.setHumidity_min(humidity_min);
                currentUser.setTemperature_max(temperature_max);
                currentUser.setTemperature_min(temperature_min);
                currentUser.setPhone(phone);

                //We update the DB with this news values
                DatabaseHelper.updateUser(currentUser); //Modifier addInformations

                //Send to the next activity
                Intent intent;
                intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("name", currentUser.getUsername());
                startActivity(intent);



            }


        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        confirm_button = (Button)findViewById(R.id.confirm_button);
        edit_min_battery = (EditText)findViewById(R.id.edit_min_battery);
        edit_max_battery = (EditText)findViewById(R.id.edit_max_battery);
        edit_min_hum = (EditText)findViewById(R.id.edit_min_hum);
        edit_max_hum = (EditText)findViewById(R.id.edit_max_hum);
        edit_min_temp = (EditText)findViewById(R.id.edit_min_temp);
        edit_max_temp = (EditText)findViewById(R.id.edit_max_temp);
        edit_phone = (EditText)findViewById(R.id.edit_phone);


    }
}
