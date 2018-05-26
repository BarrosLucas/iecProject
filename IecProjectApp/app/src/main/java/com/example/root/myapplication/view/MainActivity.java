package com.example.root.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import com.example.root.myapplication.DAO.ControllerDatabase;
import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.R;
import com.example.root.myapplication.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    ControllerDatabase controllerDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controllerDatabase = new ControllerDatabase(this);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivity(intent);
                finish();
                //Avaliar se já está logado
                //Avaliar se já está cadastrado
            }
        }, 5000);

        final ControllerDatabase controllerDatabase = new ControllerDatabase(this);
        if(controllerDatabase.loadData() != null){
            Login login = controllerDatabase.loadData();
            Call<Login> call = new RetrofitConfig().getLoginService().login(login);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if(response.isSuccessful()){
                        LoginDao.loginDAO = response.body();
                        //NextScreen
                    }else{
                        controllerDatabase.delete();
                        Toast.makeText(getBaseContext(),"Login ou senha incorretos",Toast.LENGTH_SHORT).show();
                        goToLoginScreen();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getBaseContext(),"Falha na comunicação",Toast.LENGTH_SHORT);
                    t.printStackTrace();
                    goToLoginScreen();
                }
            });
        }else{
            goToLoginScreen();
        }

    }

    public void goToLoginScreen(){
        Intent intent = new Intent(getBaseContext(),LoginScreen.class);
        startActivity(intent);
        finish();
    }
}