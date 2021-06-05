package com.example.simplewechat;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.simplewechat.ui.friends.FriendsFragment;
import com.example.simplewechat.ui.message.MessageFragment;
import com.example.simplewechat.ui.message.MessageViewModel;
import com.example.simplewechat.ui.mine.MineFragment;
import com.example.simplewechat.ui.news.NewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class IndexActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        setDefaultFragment();

        BottomNavigationView navView=findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.message_id:
                    fragmentManager= getSupportFragmentManager();
                    transaction= fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, new MessageFragment());
                    transaction.commit();
                    return true;
                case R.id.friends_id:
                    fragmentManager= getSupportFragmentManager();
                    transaction= fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, new FriendsFragment());
                    transaction.commit();
                    return true;
                case R.id.news_id:
                    fragmentManager= getSupportFragmentManager();
                    transaction= fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, new NewsFragment());
                    transaction.commit();
                    return true;
                case R.id.mine_id:
                    fragmentManager= getSupportFragmentManager();
                    transaction= fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, new MineFragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

        private void setDefaultFragment(){
            fragmentManager=getSupportFragmentManager();
            transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.content, new MessageFragment());
            transaction.commit();
        };

    @Override
    protected void onRestart() {
        super.onRestart();
        int id = getIntent().getIntExtra("flag", 1);
        if (id == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new NewsFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}