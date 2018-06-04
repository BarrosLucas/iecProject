package com.example.root.myapplication.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.myapplication.R;

public class DiscoverDevices extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_devices);

        listView = (ListView) findViewById(R.id.enable_device);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1001);
        }

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver,filter);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                adapter.add(device.getName()+"\n"+device.getAddress());
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String) adapter.getItem(position);
                        String devName = item.substring(0,item.indexOf("\n"));
                        //Toast.makeText(getBaseContext(),"Dispositivo: "+devName,Toast.LENGTH_SHORT).show();
                        String devAddress = item.substring(item.indexOf("\n")+1,item.length());
                        finishIntent(devAddress);
                    }
                });
            }
        }
    };

    public void finishIntent(String devAddress){
        InitialScreen.connect(devAddress);
        finish();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
