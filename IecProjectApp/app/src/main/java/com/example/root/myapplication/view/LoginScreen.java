package com.example.root.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.myapplication.DAO.ControllerDatabase;
import com.example.root.myapplication.DAO.LoginDao;
import com.example.root.myapplication.Model.Login;
import com.example.root.myapplication.R;
import com.example.root.myapplication.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    Button loginButton;
    EditText username,password;
    CheckBox checkBox;
    ControllerDatabase controllerDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_field);
        password = (EditText) findViewById(R.id.password_field);

        checkBox = (CheckBox) findViewById(R.id.connect);

        loginButton = (Button) findViewById(R.id.login_button);
        controllerDatabase = new ControllerDatabase(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals(Test.userlogin.getUsername()) && (password.getText().toString().equals(Test.userlogin.getPassword()))){
                    if(checkBox.isChecked()){
                        controllerDatabase.insertLogin(Test.userlogin.getUsername(),Test.userlogin.getPassword());
                        Log.i("Conectado","Conectou");
                    }
                    Intent intent = new Intent(getBaseContext(),InitialScreen.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Usuario ou senha errado",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

                    final com.example.root.myapplication.Model.Login login = new com.example.root.myapplication.Model.Login();
                    login.setUsername(username.getText().toString());
                    login.setPassword(password.getText().toString());


                    Call<com.example.root.myapplication.Model.Login> call = new RetrofitConfig().getLoginService().login(login);
                    call.enqueue(new Callback<com.example.root.myapplication.Model.Login>() {
                        @Override
                        public void onResponse(Call<com.example.root.myapplication.Model.Login> call, Response<com.example.root.myapplication.Model.Login> response) {
                            if(response.isSuccessful()){
                                LoginDao.loginDAO = response.body();
                                if(checkBox.isChecked()){
                                    controllerDatabase.insertLogin(response.body().getUsername(),response.body().getPassword());
                                }
                                Intent intent = new Intent(getBaseContext(),InitialScreen.class);
                                startActivity(intent);
                                finish();
                                //NextScreem
                            }else{
                                Toast.makeText(getBaseContext(),"Login e senha não correspondem",Toast.LENGTH_SHORT).show();
                                response.errorBody();
                            }
                        }

                        @Override
                        public void onFailure(Call<com.example.root.myapplication.Model.Login> call, Throwable t) {
                            Toast.makeText(getBaseContext(),"Falha ao conectar",Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }
            }
        });*/
    }
    public void createAccount(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
