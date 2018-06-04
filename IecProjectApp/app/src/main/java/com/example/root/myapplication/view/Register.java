package com.example.root.myapplication.view;

import com.example.root.myapplication.Model.User;
import com.example.root.myapplication.R;
import com.example.root.myapplication.controller.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText textField;
    Button next,back;
    private static int step = 0;

    private User newUser;

    private String firstName,lastName, username, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_register);


        textField = (EditText) findViewById(R.id.text_field);
        next = (Button) findViewById(R.id.next_button);
        back = (Button) findViewById(R.id.back_button);

        generateContent();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textField.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Preeencha o campo corretamente",Toast.LENGTH_SHORT).show();
                }else {
                    if (step == 0) {
                        firstName = textField.getText().toString();
                    } else if (step == 1) {
                        lastName = textField.getText().toString();
                    } else if (step == 2) {
                        username = textField.getText().toString();
                    } else if (step == 3) {
                        password = textField.getText().toString();
                    } else if (step == 4) {
                        confirmPassword = textField.getText().toString();
                    }

                if(step == 4){
                    confirmPassword = textField.getText().toString();
                    if (confirmPassword.equals(password)) {
                        newUser = new User();
                        newUser.setFirstName(firstName);
                        newUser.setLastName(lastName);
                        newUser.setUsername(username);
                        newUser.setPassword(password);

                        Test.users.add(newUser);
                        finish();

/*                        Call<User> call = new RetrofitConfig().getUserService().createUser(newUser);
                        call.enqueue(new Callback<User>() {

                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getBaseContext(),"Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(getBaseContext(),response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });*/
                        }else {
                            Toast.makeText(getBaseContext(), "Senhas não correspondem", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        step++;
                        generateContent();
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step --;
                generateContent();
            }
        });


    }



    private void generateContent(){
        if(step == 0){
            textField.setText("");
            textField.setHint("Primeiro Nome");
            textField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.INVISIBLE);
        } else if(step == 1){
            textField.setText("");
            textField.setHint("Segundo Nome");
            textField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 2){
            textField.setText("");
            textField.setHint("Nome do Usuário");
            textField.setInputType(InputType.TYPE_CLASS_TEXT);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 3){
            textField.setText("");
            textField.setHint("Senha");
            textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 4){
            textField.setText("");
            textField.setHint("Confirmar Senha");
            textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            next.setText("CADASTRAR");
        }
    }
}
