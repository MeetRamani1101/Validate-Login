package com.example.validatelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView t1,t2,t3;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t1=findViewById(R.id.textView1);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        Intent intent=getIntent();
        String f=intent.getStringExtra("NAME");
        String s=intent.getStringExtra("MOBILE");
        String t=intent.getStringExtra("EMAIL");
        t1.setText(f);
        t2.setText(s);
        t3.setText(t);
    }
}