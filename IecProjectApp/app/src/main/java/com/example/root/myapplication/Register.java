package com.example.root.myapplication;

import com.example.root.myapplication.FontAwesome.FontManager;
import com.example.root.myapplication.R;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        next = (Button) findViewById(R.id.next_button);
        back = (Button) findViewById(R.id.back_button);

        textField = (EditText) findViewById(R.id.text_field);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.microphone), iconFont);
    }
    private void generateContent(){
        if(step == 1){
            //textField.setText(R.str);
        }
    }
}
