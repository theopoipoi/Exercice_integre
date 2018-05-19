package com.example.theopoipoi.exercice_integre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity  {

    int counter = 3;

    //Declaration EditTexts
    EditText edit_text_username,edit_text_password;

    //Declaration Button
    Button sign_in_button;

    //Declaration DatabaseHelper
    DatabaseHelper DatabaseHelper;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHelper = new DatabaseHelper(this);
        initViews();
        intent = new Intent(getApplicationContext(), SecondActivity.class);


        //set click event of login button
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                //if (validate()) {

                    //Get values from EditText fields
                    String Username = edit_text_username.getText().toString();
                    String Password = edit_text_password.getText().toString();
                    Log.w("Length password", Integer.toString(Password.length()));
                    if(Username.matches("")|| Password.length() != 4) {
                        Toast.makeText(getApplicationContext(), "Unsuccessfull request, please fill in all the fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Authenticate user
                        User currentUser = new User();
                        currentUser.setUsername(Username);
                        currentUser.setPassword(Integer.parseInt(Password));


                        if (DatabaseHelper.checkUser(Username)) {
                            Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Successfully Sign in!", Toast.LENGTH_SHORT).show();
                            DatabaseHelper.addUser(currentUser);
                            System.out.println(DatabaseHelper.getAllUser());
                        }
                        intent.putExtra("name", currentUser.getUsername());
                        startActivity(intent);
                        }




            }
        });


    }

    /*//this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }*/

    //this method is used to connect XML views to its Objects
    private void initViews() {
        sign_in_button = (Button)findViewById(R.id.login_sign_in_button);
        edit_text_username = (EditText)findViewById(R.id.login_username);
        edit_text_password = (EditText)findViewById(R.id.login_password);

    }

    /*//This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }*/

    /*//This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = edit_text_username.getText().toString();
        String Password = edit_text_password.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }*/


}



