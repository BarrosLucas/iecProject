package com.example.root.myapplication.view;

import com.example.root.myapplication.FontAwesome.FontManager;
import com.example.root.myapplication.R;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText textField;
    Button next,back;
    private static int step = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_register);

        generateContent();

        next = (Button) findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step ++;
                generateContent();
            }
        });
        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step --;
                generateContent();
            }
        });

        textField = (EditText) findViewById(R.id.text_field);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.microphone), iconFont);
    }



    private void generateContent(){
        if(step == 0){
            textField.setHint("Primeiro Nome");
            textField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.INVISIBLE);
        } else if(step == 1){
            textField.setHint("Segundo Nome");
            textField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 2){
            textField.setHint("Nome do Usuário");
            textField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 3){
            textField.setHint("Senha");
            textField.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }else if(step == 4){
            textField.setHint("Confirmar Senha");
            textField.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            next.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }
    }
}
