package com.example.root.myapplication.Register;

import com.example.root.myapplication.FontAwesome.FontManager;
import com.example.root.myapplication.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InitialRegister extends AppCompatActivity {
    TextView microphoneTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_register);

        microphoneTextView = (TextView)findViewById(R.id.microphone);
        microphoneTextView.setTypeface(FontManager.getTypeface(this,FontManager.FONTAWESOME));
    }
}
