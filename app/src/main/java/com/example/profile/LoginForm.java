package com.example.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {

    private EditText username;
    private EditText passworda;
    private Button btnLogin;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        username=(EditText)findViewById(R.id.txt_username);
        passworda=(EditText)findViewById(R.id.txt_password);
        btnLogin=(Button)findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(),passworda.getText().toString());
            }
        });
    }

    public void validate(String user ,String password)
    {
        String etusername=username.getText().toString();
        String etpassword=passworda.getText().toString();

        if(!user.isEmpty())
        {
            if(!password.isEmpty())
            {
                if(etusername.equals("admin"))
                {
                    if(etpassword.equals("admin"))
                    {
                        Intent i=new Intent(LoginForm.this,MainActivity.class);
                        ProfileFragment.usernametxt=etusername;
                        ProfileFragment.passwordtxt=etpassword;
                        startActivity(i);
                    }
                    else
                    {
                        AlertDialog.Builder alert01=new AlertDialog.Builder(this);
                        alert01.setMessage("Please enter a valid password").setNegativeButton("Try Again",null).create().show();
                    }
                }
                else
                {
                    Toast.makeText(LoginForm.this,"Please enter a valid username",Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(LoginForm.this,"Password cant be empty",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(LoginForm.this,"Email cant be empty",Toast.LENGTH_LONG).show();
        }

    }
}