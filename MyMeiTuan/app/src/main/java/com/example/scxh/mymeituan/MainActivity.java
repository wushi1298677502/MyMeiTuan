package com.example.scxh.mymeituan;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

//    private static final int TAB_LIST = 0;
//    private static final int TAB_GRID = 1;
//    private static final int TAB_PROGRESS_BAR = 2;
//    private static final int MORE = 3;
    public TabHost mtabhost;
    public  LayoutInflater layoutInflater;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabhost = getTabHost();

        layoutInflater = LayoutInflater.from(this);
        TabHost.TabSpec tabSpec1 = mtabhost.newTabSpec("tab1");
        tabSpec1.setIndicator(indicatorview(0));//可以设定文字或者view
        tabSpec1.setContent(new Intent(this, MTActivity.class));

        TabHost.TabSpec tabSpec2 = mtabhost.newTabSpec("tab2");
        tabSpec2.setIndicator(indicatorview(1));
        tabSpec2.setContent(new Intent(this, Sj_Activity.class));

        TabHost.TabSpec tabSpec3 = mtabhost.newTabSpec("tab3");
        tabSpec3.setIndicator(indicatorview(2));
        tabSpec3.setContent(new Intent(this, My_Activity.class));

        TabHost.TabSpec tabSpec4 = mtabhost.newTabSpec("tab4");
        tabSpec4.setIndicator(indicatorview(3));
        tabSpec4.setContent(new Intent(this, More_Activity.class));

        mtabhost.addTab(tabSpec1);
        mtabhost.addTab(tabSpec2);
        mtabhost.addTab(tabSpec3);
        mtabhost.addTab(tabSpec4);
    }
    public View indicatorview(int pageNo){

        View view1 = layoutInflater.inflate(R.layout.activity_tab_demo1,null);
        ImageView iconimg = (ImageView) view1.findViewById(R.id.indicator_imageview);
        TextView textView = (TextView) view1.findViewById(R.id.indicator_textview);
        switch (pageNo){
            case 0:
                iconimg.setImageResource(R.drawable.shouye);
                textView.setText("首页");
                break;
            case 1:
                iconimg.setImageResource(R.drawable.shangjia);
                textView.setText("商家");
                break;
            case 2:
                iconimg.setImageResource(R.drawable.users);
                textView.setText("我的");
                break;
            case 3:
                iconimg.setImageResource(R.drawable.more);
                textView.setText("设置");
                break;
        }
        return view1;
    }

}

