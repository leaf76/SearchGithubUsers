package com.leaf76.githubusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;

import com.leaf76.githubusers.adapters.UserAdapter;
import com.leaf76.githubusers.adapters.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String tag = UserFragment.TAG;
        if(getSupportFragmentManager().findFragmentByTag(tag) == null){
            UserFragment fragment = UserFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, tag)
                    .commit();
        }
    }
}
