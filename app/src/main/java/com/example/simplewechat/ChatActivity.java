package com.example.simplewechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplewechat.component.MsgAdapter;
import com.example.simplewechat.domain.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();
    private TextView chatPerson;
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name=getIntent().getStringExtra("name");
        chatPerson=findViewById(R.id.chat_person);
        chatPerson.setText(name);

        initMsg();

        inputText=findViewById(R.id.input_text);
        send=findViewById(R.id.send);
        msgRecyclerView=findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context;
                context=inputText.getText().toString();
                if(!"".equals(context)){
                    Msg msg=new Msg(context,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsg(){
        Msg msg1=new Msg("你好！最近过得怎么样？",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("混得一般般，你呢？",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3=new Msg("什么时候找时间聚一聚吧。",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}