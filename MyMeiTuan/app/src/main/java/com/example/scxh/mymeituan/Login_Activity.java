package com.example.scxh.mymeituan;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends AppCompatActivity {

    public TextInputEditText username, mpassword;
    public Button mbutton, mbutton1, mbutton2, mbutton3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1_layout);

        username = (TextInputEditText) findViewById(R.id.login_textinputedittext_one);
        mpassword = (TextInputEditText) findViewById(R.id.login_textinputedittext_two);
        mbutton = (Button) findViewById(R.id.clickbutton);
        mbutton1 = (Button) findViewById(R.id.btn1);
        mbutton2 = (Button) findViewById(R.id.btn2);

        mbutton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                username.setText(null);
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mpassword.setText(null);
            }
        });
        mbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = username.getText().toString();
                String name1 = mpassword.getText().toString();
                if (!name.equals("admin")) {
                    username.setError("输入错误");
                }
                if (!name1.equals("123456")) {
                    mpassword.setError("输入错误");

                }
                mothod(view);
            }
        });


    }

    public void ToggleButtonmothod(View v) {
        Intent intent = new Intent(this,Show_Activity.class);
        startActivity(intent);
    }

    public void mothod(View v) {

        String name = username.getText().toString();
        String name1 = mpassword.getText().toString();
        if ((name.equals("admin")) && (name1.equals("123456"))) {
            Intent intent = new Intent(this, Show_Activity.class);
            startActivity(intent);
        }

    }

    public void contactFacebook(View v) {
        Intent intent = new Intent(this, Show_Activity.class);
        startActivity(intent);
    }
}



