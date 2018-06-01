package com.example.root.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.Model.User;
import com.example.root.myapplication.R;
import com.example.root.myapplication.retrofit.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ListView listView;
    public static ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.listUsers);
        final SimplesAdapter simplesAdapter = new SimplesAdapter(this);


        simplesAdapter.setUser(Test.users);
        Toast.makeText(this,"Tamanho: "+Test.users.size(),Toast.LENGTH_SHORT).show();
        listView.setAdapter(simplesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                messages = new ArrayList<Message>();
                for(Message m: Test.messageArrayList){
                    if(m.getSender().getUsername().equals(Test.userlogin.getUsername()) && m.getRecipient().getUsername().equals(simplesAdapter.getUser().get(position).getUsername())){
                        messages.add(m);
                    }
                    if(m.getRecipient().getUsername().equals(Test.userlogin.getUsername()) && m.getSender().getUsername().equals(simplesAdapter.getUser().get(position).getUsername())){
                        messages.add(m);
                    }
                }

                Intent intent = new Intent(getBaseContext(),TalkActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*Call<ArrayList<User>> call = new RetrofitConfig().getUserService().getUsers();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()){
                    simplesAdapter.setUser(response.body());
                    listView.setAdapter(simplesAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Call<ArrayList<Message>> call = new RetrofitConfig().getMessageService().getMessagesDialog(LoginDao.loginDAO.getUsername(),simplesAdapter.getUser().get(position).getUsername());
                            call.enqueue(new Callback<ArrayList<Message>>() {
                                @Override
                                public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                                    if(response.isSuccessful()){
                                        messages = response.body();
                                        Intent intent = new Intent(getBaseContext(),TalkActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getBaseContext(),"Houve algum erro",Toast.LENGTH_SHORT).show();
                                        Log.e("Fail",response.errorBody().toString());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                                    Toast.makeText(getBaseContext(),"Falha na conexão",Toast.LENGTH_SHORT).show();
                                    t.printStackTrace();
                                }
                            });
                        }
                    });
                }else{
                    Toast.makeText(getBaseContext(),"Você não tem permissão",Toast.LENGTH_SHORT).show();
                    Log.e("Fail",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Falha na comunicação",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.initial_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }
}
