package com.example.simplewechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplewechat.domain.User;

import org.litepal.crud.DataSupport;

import java.util.List;

//登录界面
public class LoginActivity extends AppCompatActivity {
    private EditText login_input_name;
    private EditText login_input_password;
    private TextView login_tip_text;
    private CheckBox checkBox_RememberPwd;
    private Button loginBtn;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_input_name=findViewById(R.id.login_input_name);
        login_input_password=findViewById(R.id.login_input_password);
        login_tip_text=findViewById(R.id.login_tip_text);
        checkBox_RememberPwd=findViewById(R.id.checkbox_rememberPwd);
        loginBtn=findViewById(R.id.login_btn);

        //判断复选框是否选中
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=pref.getBoolean("remember_pwd",false);
        if(isRemember){
            String name=pref.getString("name","");
            String password=pref.getString("password","");
            login_input_name.setText(name);
            login_input_password.setText(password);
            checkBox_RememberPwd.setChecked(true);
        }

        //点击登录按钮
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否和数据库的数据匹配
                //判断是否记住密码

                String userName=login_input_name.getText().toString();
                String userPassword=login_input_password.getText().toString();

                if(userName==null){
                    login_tip_text.setText("请输入用户名");
                }else if(userPassword==null){
                    login_tip_text.setText("请输入密码");
                }else{
                    List<User> userList= DataSupport.where("name=? and password=?",userName,userPassword).find(User.class);
                    if(userList.size()==1){
                        //匹配
                        //判断复选框是否选中，若选中，将数据存储到SharedPreferences中
                        editor=pref.edit();
                        if(checkBox_RememberPwd.isChecked()){
                            editor.putBoolean("remember_pwd",true);
                            editor.putString("name",userName);
                            editor.putString("password",userPassword);

                        }else{
                            //若没选中
                            editor.clear();
                        }
                        editor.apply();

                        //登录成功，跳转到个人页面
                        Intent intent=new Intent(LoginActivity.this,IndexActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        login_tip_text.setText("用户名或密码错误");
                    }
                }
            }
        });
    }
}