package com.example.scxh.mymeituan;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class More_Activity extends AppCompatActivity {

    boolean back = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_layout);

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
