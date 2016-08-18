package com.example.scxh.mymeituan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class City_Activity extends AppCompatActivity implements View.OnClickListener {
    int resultCode;
    Button button1,button2,button3,button4,button5;
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_layout);

        textView = (TextView) findViewById(R.id.city_textview);
        button1= (Button) findViewById(R.id.city_button_one);
        button2= (Button) findViewById(R.id.city_button_two);
        button3= (Button) findViewById(R.id.city_button_three);
        button4= (Button) findViewById(R.id.city_button_four);
        button5= (Button) findViewById(R.id.city_button_five);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        String city = bundle.getString("CITY");
        textView.setText(city);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = getIntent();
        String str1 = button1.getText().toString();
        String str2 = button2.getText().toString();
        String str3 = button3.getText().toString();
        String str4 = button4.getText().toString();
        String str5 = button5.getText().toString();
        switch (id){
            case R.id.city_button_one:
                resultCode = 1;//TODO 回执码,在返回时做判断
                intent.putExtra("ONE",str1);
                setResult(resultCode,intent);
                finish();
                break;
            case R.id.city_button_two:
                resultCode = 2;//TODO 回执码吧 成成
                intent.putExtra("TWO",str2);
                setResult(resultCode,intent);
                finish();
                break;
            case R.id.city_button_three:
                resultCode = 3;//TODO 回执码吧 成成
                intent.putExtra("THREE",str3);
                setResult(resultCode,intent);
                finish();
                break;
            case R.id.city_button_four:
                resultCode = 4;//TODO 回执码吧 成成
                intent.putExtra("FOUR",str4);
                setResult(resultCode,intent);
                finish();
                break;
            case R.id.city_button_five:
                resultCode = 5;//TODO 回执码吧 成成
                intent.putExtra("FIVE",str5);
                setResult(resultCode,intent);
                finish();
                break;
        }

    }
}
