package com.example.root.myapplication.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.root.myapplication.R;

import java.util.List;

public class TalkActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        listView = (ListView) findViewById(R.id.chat);
        SimplesAdapterTalk simplesAdapterTalk = new SimplesAdapterTalk(this);
        simplesAdapterTalk.setMessages(InitialScreen.messages);
        listView.setAdapter(simplesAdapterTalk);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

    }
}
