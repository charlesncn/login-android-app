package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonLogin;
    TextView mTextViewAlreadyRegisted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db =new DatabaseHelper(this);

        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextEmail=(EditText)findViewById(R.id.edittext_email);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mTextConfirmPassword=(EditText)findViewById(R.id.edittext_confirmpassword);
        mButtonLogin=(Button)findViewById(R.id.button_login);
        mTextViewAlreadyRegisted=(TextView)findViewById(R.id.textview_register);

        mTextViewAlreadyRegisted.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent LoginIntent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnfpwd = mTextConfirmPassword.getText().toString().trim();
                if(pwd.equals(cnfpwd)){
                    long val = db.addUser(user,pwd);
                    if(val>0){
                        Toast.makeText(RegisterActivity.this, "Successifully Registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin =new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Registeration error!",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error! The password you entered is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}