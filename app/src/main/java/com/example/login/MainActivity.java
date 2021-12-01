package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonSIGN_IN;
    TextView mTextViewRegister;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new  String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        db =new DatabaseHelper(this);

        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mButtonSIGN_IN=(Button)findViewById(R.id.button_login);
        mTextViewRegister=(TextView)findViewById(R.id.textview_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mButtonSIGN_IN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true){
                    Toast.makeText(MainActivity.this, "Successifully Logged In",Toast.LENGTH_SHORT).show();
                    Intent HomeScreen = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(HomeScreen);
                }
                else{
                    Toast.makeText(MainActivity.this, "Error! The username/password you entered is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}