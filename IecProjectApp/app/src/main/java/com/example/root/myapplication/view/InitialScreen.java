package com.example.root.myapplication.view;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Message;
import com.example.root.myapplication.Model.User;
import com.example.root.myapplication.R;
import com.example.root.myapplication.callback.CallbackMessage;
import com.example.root.myapplication.controller.ConnectionThread;
import com.example.root.myapplication.controller.SimplesAdapter;
import com.example.root.myapplication.controller.Test;

import java.util.ArrayList;

public class InitialScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static Context context;
    private ListView listView;
    public static ArrayList<Message> messages;
    public static User userTalk;
    public TextView nav_fullname,nav_username;

    public static int ENABLE_BLUETOOTH = 1;
    public static int SELECT_PAIRED_DEVICE = 2;
    public static int SELECT_DISCOVERED_DEVICE = 3;

    CallbackMessage callbackMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        callbackMessage = new CallbackMessage();
        Intent intent = new Intent();
        callbackMessage.startService(intent);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);



        nav_fullname = (TextView) header.findViewById(R.id.fullname_nav);
        nav_username = (TextView) header.findViewById(R.id.username_nav);



        /*nav_fullname.setText(LoginDao.loginDAO.getFirstName().toUpperCase()+" "+LoginDao.loginDAO.getLastName().toUpperCase());
        nav_username.setText("@"+LoginDao.loginDAO.getUsername());*/
        nav_fullname.setText(Test.userlogin.getFirstName().toUpperCase()+" "+Test.userlogin.getLastName().toUpperCase());
        nav_username.setText("@"+Test.userlogin.getUsername());

        listView = (ListView) findViewById(R.id.listUsers);
        final SimplesAdapter simplesAdapter = new SimplesAdapter(this);


        LoginDao.users = Test.users;
        simplesAdapter.setUser(LoginDao.users);
        Toast.makeText(this,"Tamanho: "+Test.users.size(),Toast.LENGTH_SHORT).show();
        listView.setAdapter(simplesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userTalk = simplesAdapter.getUser().get(position);
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
            }
        });

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
        int id = item.getItemId();
        if(id == R.id.nav_talk){

        }else if(id == R.id.nav_device){
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter == null){
                Toast.makeText(this,"Não há suporte para bluetooth neste dispositivo",Toast.LENGTH_SHORT).show();
            }else{
                if(!bluetoothAdapter.isEnabled()){
                    Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetooth,ENABLE_BLUETOOTH);
                }else{
                    Intent searchPairedDevicesIntent = new Intent(this,PairedDevice.class);
                    startActivityForResult(searchPairedDevicesIntent,SELECT_PAIRED_DEVICE);
                }
            }
        }else if(id == R.id.nav_logout){

        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ENABLE_BLUETOOTH){
            if(resultCode == RESULT_OK){
                //Ativou o bluetooth
                Intent searchPairedDevicesIntent = new Intent(this,PairedDevice.class);
                startActivityForResult(searchPairedDevicesIntent,SELECT_PAIRED_DEVICE);
            }else{
                Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == SELECT_PAIRED_DEVICE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"Selecionou o dispositivo: "+data.getStringExtra("btDevAddress"),Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static void connect(String devAddress){
        MainActivity.connectionThread = new ConnectionThread(devAddress);
        MainActivity.connectionThread.start();
    }

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message message){
            Bundle bundle = message.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);

            if(dataString.equals("---N")){
                Toast.makeText(context,"Falha na conexão com o dispositivo",Toast.LENGTH_SHORT).show();
            }else if(dataString.equals("---S")){
                Toast.makeText(context,"Conectado",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
        callbackMessage.onDestroy();
    }

}
