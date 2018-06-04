package com.example.root.myapplication.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;

import com.example.root.myapplication.view.InitialScreen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

public class ConnectionThread extends Thread {
    BluetoothSocket bluetoothSocket = null;
    BluetoothServerSocket bluetoothServerSocket = null;
    InputStream inputStream = null;
    OutputStream outputStream = null;
    String bluetoothDeviceAddress = null;
    String myUUID = "00001101-0000-1000-8000-00805F9B34FB";
    boolean server;
    boolean running = false;

    public ConnectionThread(){
        this.server = true;
    }
    public ConnectionThread(String bluetoothDeviceAddress){
        this.server = false;
        this.bluetoothDeviceAddress = bluetoothDeviceAddress;
    }

    public void run(){
        this.running = true;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(this.server){

        }else{
            try{
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(bluetoothDeviceAddress);
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));
                bluetoothAdapter.cancelDiscovery();
                if(bluetoothSocket != null){
                    bluetoothSocket.connect();
                }
            }catch (IOException e){
                e.printStackTrace();
                toMainActivity("---N".getBytes());
            }
        }
        if(bluetoothSocket != null){
            toMainActivity("---S".getBytes());
            try{
                inputStream = bluetoothSocket.getInputStream();
                outputStream = bluetoothSocket.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytes;
                while(running){
                    bytes = inputStream.read();
                    toMainActivity(Arrays.copyOfRange(buffer,0,bytes));
                }
            }catch (IOException e){
                e.printStackTrace();
                toMainActivity("---N".getBytes());
            }
        }
    }
    public void toMainActivity(byte[] data){
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data",data);
        message.setData(bundle);
        InitialScreen.handler.sendMessage(message);
    }
    public void write(byte[] data){
        if(outputStream != null){
            try{
                outputStream.write(data);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            toMainActivity("---N".getBytes());
        }
    }
    public void cancel(){
        try{
            running = false;
            bluetoothSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        running = false;
    }
}
