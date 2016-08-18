package com.example.scxh.mymeituan;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class My_Activity extends AppCompatActivity {

   public TextView mtextview;
    boolean back = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_layout);

        mtextview = (TextView) findViewById(R.id.my_textview_onclick);

        mtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
//        super.onBackPressed();
        Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
        if(back==true){
            finish();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                back=true;
                SystemClock.sleep(2000);
                back=false;
            }
        }).start();

    }

}
