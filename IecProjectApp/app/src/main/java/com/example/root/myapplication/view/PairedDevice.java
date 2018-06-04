package com.example.root.myapplication.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.myapplication.R;

import java.util.Set;

public class PairedDevice extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired_device);

        ListView lv = (ListView) findViewById(R.id.device_paired);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
        if(pairedDevices.size()>0){
            for(BluetoothDevice device: pairedDevices){
                adapter.add(device.getName()+"\n"+device.getAddress());
            }
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) adapter.getItem(position-1);
                    String devName = item.substring(0,item.indexOf("\n"));
                    String devAddress = item.substring(item.indexOf("\n")+1,item.length());

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("btDevName",devName);
                    returnIntent.putExtra("btDevAddress",devAddress);
                    setResult(RESULT_OK,returnIntent);
                    finish();

            }
        });
    }



    public void searchOnlineDevices(View view){
        Intent intent = new Intent(this,DiscoverDevices.class);
        startActivity(intent);
        finish();
    }
}
