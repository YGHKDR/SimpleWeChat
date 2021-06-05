package com.example.simplewechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.simplewechat.component.NewsAdapter;
import com.example.simplewechat.ui.news.NewsFragment;

public class MakeMomentActivity extends AppCompatActivity {

    private Button makeMomentBtn;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_moment);

        text=findViewById(R.id.momentText);

        makeMomentBtn=findViewById(R.id.make_moment);
        makeMomentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(text.toString())){
                    NewsFragment.setContent(text.toString());
                }
                NewsFragment.update();  //直接调用NewsFragment的静态函数 内的参数也需要是静态的
                Intent intent=new Intent(MakeMomentActivity.this, IndexActivity.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
                MakeMomentActivity.this.finish();
            }
        });
    }
}