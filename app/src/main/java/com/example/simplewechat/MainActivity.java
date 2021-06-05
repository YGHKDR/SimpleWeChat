package com.example.simplewechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();

        Button singin=findViewById(R.id.signin);
        Button newUser=findViewById(R.id.newUser);
        singin.setOnClickListener(this);
        newUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                //登录页面
                Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.newUser:
                //注册页面
                Intent registerIntent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                break;
            default:
                break;
        }
    }
}