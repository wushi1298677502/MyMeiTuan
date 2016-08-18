package com.example.scxh.mymeituan;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Pager_Activity extends AppCompatActivity implements View.OnClickListener{


    public ViewPager mviewpager;
    public ImageView mimageview1,mimageview2,mimageview3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_layout);

        mviewpager = (ViewPager) findViewById(R.id.adapter_dot_viewpager);
        mimageview1 = (ImageView) findViewById(R.id.adapter_imageview1);
        mimageview1.setOnClickListener(this);
        mimageview2 = (ImageView) findViewById(R.id.adapter_imageview2);
        mimageview2.setOnClickListener(this);
        mimageview3 = (ImageView) findViewById(R.id.adapter_imageview3);
        mimageview3.setOnClickListener(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.activity_demo_image1, null);
        View view2 = layoutInflater.inflate(R.layout.activity_demo_image2, null);
        View view3 = layoutInflater.inflate(R.layout.activity_demo_image3, null);
        View view4 = layoutInflater.inflate(R.layout.activity_guidance_layout, null);
        List<View> list = new ArrayList<>();

        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);

        ViewAdapter adapter = new ViewAdapter(list);
        mviewpager.setAdapter(adapter);



        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // TODO: 2016/6/11 onPageScrollec指明下一页面翻了多少，0 < positionOffset< 1,在做动画的时候可以使用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logs.e("Pager_Activity---positionOffset》》》》》》》》》》》"+positionOffset);
            }

            @Override
            // TODO: 2016/6/11  onPageSelected 表示当前的页面，position 从零开始表示第一页，设定原点图标是为了告诉用户当前页面的位置
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mimageview1.setImageResource(R.drawable.page);
                        mimageview2.setImageResource(R.drawable.page_now);
                        mimageview3.setImageResource(R.drawable.page_now);
                        break;
                    case 1:
                        mimageview1.setImageResource(R.drawable.page_now);
                        mimageview2.setImageResource(R.drawable.page);
                        mimageview3.setImageResource(R.drawable.page_now);
                        break;
                    case 2:
                        mimageview1.setImageResource(R.drawable.page_now);
                        mimageview2.setImageResource(R.drawable.page_now);
                        mimageview3.setImageResource(R.drawable.page);
                        break;
                    case 3:
                        Intent intent = new Intent(Pager_Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

            // TODO: 2016/6/11  onPageScrollStateChanged 指代页面翻页信息，有1，2, 0
            // TODO: 2016/6/11 1,表示有动作，2 ，表示达到零界点，0，是全部翻完
            public void onPageScrollStateChanged(int state) {
                Logs.e("Pager_Activity---onPageScrollStateChanged》》》》》》》》》》》"+state);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adapter_imageview1:
                mimageview1.setImageResource(R.drawable.page);
                mimageview2.setImageResource(R.drawable.page_now);
                mimageview3.setImageResource(R.drawable.page_now);
                mviewpager.setCurrentItem(0);
                break;
            case R.id.adapter_imageview2:
                mimageview1.setImageResource(R.drawable.page_now);
                mimageview2.setImageResource(R.drawable.page);
                mimageview3.setImageResource(R.drawable.page_now);
                mviewpager.setCurrentItem(1);
                break;
            case R.id.adapter_imageview3:
                mimageview1.setImageResource(R.drawable.page_now);
                mimageview2.setImageResource(R.drawable.page_now);
                mimageview3.setImageResource(R.drawable.page);
                mviewpager.setCurrentItem(2);
                break;
        }
    }

    // TODO: 2016/6/11 ViewAdapter 继承PagerAdapter,可以实现翻页功能，不过需要重写方法
    class ViewAdapter extends PagerAdapter {
        // TODO: 2016/6/11 定义的每一页的集合
        List<View> list = new ArrayList<>();
        public ViewAdapter(List<View> list){
            this.list = list;
        }
        // TODO: 2016/6/11 得到集合的大小
        public int getCount() {
            return list.size();
        }

        // TODO: 2016/6/11 填土view==object即可
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        // TODO: 2016/6/11 实例化每一项，根据每一页的position得到相应页面的view 然后加入到ViewGroup里面
        public Object instantiateItem(ViewGroup container, int position) {
            View view = list.get(position);
            container.addView(view);
            return view;
        }

        // TODO: 2016/6/11 销毁相应的view
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = list.get(position);
            container.removeView(view);
        }
    }

}
