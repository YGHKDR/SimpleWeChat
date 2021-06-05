package com.example.simplewechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplewechat.domain.User;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

//注册页面
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText input_name;
    private EditText input_password;
    private TextView tip_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_name=findViewById(R.id.input_name);
        input_password=findViewById(R.id.input_password);
        tip_text=findViewById(R.id.tip_text);
        Button registerBtn=findViewById(R.id.register_btn);
        Button toLoginBtn=findViewById(R.id.toLogin_btn);
        registerBtn.setOnClickListener(this);
        toLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                //注册前要检查用户名和密码是否都已经输入
                String userName=input_name.getText().toString();
                String userPassword=input_password.getText().toString();
                if(userName==null){
                    tip_text.setText("请输入用户名");
                }else if(userPassword==null){
                    tip_text.setText("请输入密码");
                }else{
                    //注册前要检查该用户名是否已存在
                    List<User> userList= DataSupport.where("name=?",userName).find(User.class);
                    if(userList.size()!=0){
                        //用户名已存在
                        tip_text.setText("用户名已存在，请重新输入");
                    }else{
                        //用户名不存在，存入LitePal中
                        User user=new User();
                        user.setName(userName);
                        user.setPassword(userPassword);
                        user.save();
                        //跳转到登录界面
                        Intent intent1=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }
                break;
            case R.id.toLogin_btn:
                //跳转到登录界面
                Intent intent2=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}