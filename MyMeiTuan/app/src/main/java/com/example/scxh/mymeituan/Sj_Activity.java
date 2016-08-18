package com.example.scxh.mymeituan;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Sj_Activity extends AppCompatActivity implements View.OnClickListener {

    public ViewPager mviewpager;
    public TextView mtextview1,mtextview2,textView,textView2;
    public ListView popupListView,popupListView2;
    public List<Student> listsssss = new ArrayList<>();
    public List<Student2> listsssss2 = new ArrayList<>();

    Spinner mspinner3;
    ArrayAdapter arrayAdapter3;

    ListView mListView1;
    ListView mListView2;
    List<MessageBean> list2 = new ArrayList<>();

    boolean back = false;
    PopupWindow popupWindow,popupWindow2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sj_layout);

        textView= (TextView) findViewById(R.id.sj_spinner_one);
        textView.setOnClickListener(this);
        textView2= (TextView) findViewById(R.id.sj_spinner_two);
        textView2.setOnClickListener(this);

        // TODO: 2016/6/23 spinner的下拉框位置dropDownVerticalOffset设定 
        mspinner3= (Spinner) findViewById(R.id.sj_spinner_three);
        String[] s3 = {"智能排序","好评优选","离我最近","人均最低"};
        arrayAdapter3 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,s3);
        mspinner3.setAdapter(arrayAdapter3);




        mviewpager = (ViewPager) findViewById(R.id.sj_viewpager);

        mtextview1 = (TextView) findViewById(R.id.sj_adapter_TextView1);
        mtextview1.setOnClickListener(this);
        mtextview2 = (TextView) findViewById(R.id.sj_adapter_TextView2);
        mtextview2.setOnClickListener(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.activity_listview1_layout, null);
        View view2 = layoutInflater.inflate(R.layout.activity_listview2_layout, null);
        List<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        ViewAdapter adapter = new ViewAdapter(list);
        mviewpager.setAdapter(adapter);

        mtextview1.setTextColor(getResources().getColor(R.color.green1));
        mtextview2.setTextColor(getResources().getColor(R.color.green2));

        // TODO: 2016/6/14  加view1是因为(R.layout.activity_demo_meituan_layout)已经解析了
        // todo 直接findViewById(R.id.gridview1)会在里面寻找，就会报空指针错
        // mListView1 = (ListView)todo view1.findViewById(R.id.sj_listview1);
        mListView1 = (ListView)view1.findViewById(R.id.sj_listview1);
        initData();// TODO: 2016/6/9 向listview 里面添加数据
        MerchantAdapter merchantAdapter1 = new MerchantAdapter(this);// TODO: 2016/6/9 自定义的listview 的子项布局
        merchantAdapter1.setList(list2);
        Logs.e("KKKKKKKKKKKKKKKKK"+list2);
        mListView1.setAdapter(merchantAdapter1);//注册


        // TODO: 2016/6/22 下面是弹出框PopupWindow的解析布局 
        LayoutInflater inflater2 = LayoutInflater.from(this);
        View popupContentView = inflater2.inflate(R.layout.activity_my_object_adapter_layout,null);
        popupListView = (ListView) popupContentView.findViewById(R.id.myobjectadapter);
        initData2();
        MyStudentAdapter myStudentAdapter = new MyStudentAdapter(this,listsssss);
        popupListView.setAdapter(myStudentAdapter);

        // TODO: 2016/6/12 PopupWindow 是系统准备好的实例化后直接用
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupContentView);
        popupWindow.setWidth(400);// TODO: 2016/6/12  设定弹出框的宽高
        popupWindow.setHeight(400);

        // TODO: 2016/6/12 以下属性保证点击其它区域，弹出的内容可以自行回收
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);


        LayoutInflater inflater3 = LayoutInflater.from(this);
        View popupContentView2 = inflater3.inflate(R.layout.activity_my_object2_adapter_layout,null);
        popupListView2 = (ListView) popupContentView2.findViewById(R.id.myobjectadapter2);
        initData3();
        MyStudentAdapter2 myStudentAdapter2 = new MyStudentAdapter2(this,listsssss2);
        popupListView2.setAdapter(myStudentAdapter2);

        // TODO: 2016/6/12 PopupWindow 是系统准备好的实例化后直接用
        popupWindow2 = new PopupWindow(this);
        popupWindow2.setContentView(popupContentView2);
        popupWindow2.setWidth(400);// TODO: 2016/6/12  设定弹出框的宽高
        popupWindow2.setHeight(400);
//        popupWindow2(popupContentView2,400,400);
        // TODO: 2016/6/12 以下属性保证点击其它区域，弹出的内容可以自行回收
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setFocusable(true);
        popupWindow2.setTouchable(true);

        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // TODO: 2016/6/11 onPageScrollec指明下一页面翻了多少，0 < positionOffset< 1,在做动画的时候可以使用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logs.e("positionOffset》》》》》》》》》》》"+positionOffset);
            }

            @Override
            // TODO: 2016/6/11  onPageSelected 表示当前的页面，position 从零开始表示第一页，设定原点图标是为了告诉用户当前页面的位置
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mtextview1.setTextColor(getResources().getColor(R.color.green1));
                        mtextview2.setTextColor(getResources().getColor(R.color.green2));

//                        mListView1 = (ListView) findViewById(R.id.sj_listview1);
//                        initData();// TODO: 2016/6/9 向listview 里面添加数据
//                        MerchantAdapter merchantAdapter1 = new MerchantAdapter(Sj_Activity.this);// TODO: 2016/6/9 自定义的listview 的子项布局
//                        merchantAdapter1.setList(list2);
//                        Logs.e("lllllllllllllll"+merchantAdapter1);
//                        mListView1.setAdapter(merchantAdapter1);//注册

                        break;
                    case 1:
                        mtextview1.setTextColor(getResources().getColor(R.color.green2));
                        mtextview2.setTextColor(getResources().getColor(R.color.green1));

                        mListView2 = (ListView) findViewById(R.id.sj_listview2);
                        initData();// TODO: 2016/6/9 向listview 里面添加数据
                        MerchantAdapter merchantAdapter2 = new MerchantAdapter(Sj_Activity.this);// TODO: 2016/6/9 自定义的listview 的子项布局
                        merchantAdapter2.setList(list2);
                        Logs.e("lllllllllllllll"+mListView2);
                        mListView2.setAdapter(merchantAdapter2);//注册
                        break;

                }
            }

            // TODO: 2016/6/11  onPageScrollStateChanged 指代页面翻页信息，有1，2, 0
            // TODO: 2016/6/11 1,表示有动作，2 ，表示达到零界点，0，是全部翻完
            public void onPageScrollStateChanged(int state) {
                Logs.e("onPageScrollStateChanged》》》》》》》》》》》"+state);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sj_adapter_TextView1:
                mtextview1.setTextColor(getResources().getColor(R.color.green1));
                mtextview2.setTextColor(getResources().getColor(R.color.green2));

                mviewpager.setCurrentItem(0);
                break;
            case R.id.sj_adapter_TextView2:
                mtextview1.setTextColor(getResources().getColor(R.color.green2));
                mtextview2.setTextColor(getResources().getColor(R.color.green1));

                mviewpager.setCurrentItem(1);
                break;
            case R.id.sj_spinner_one:
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(view);
                }
                break;
            case R.id.sj_spinner_two:
                if(popupWindow2.isShowing()){
                    popupWindow2.dismiss();
                }else{
                    popupWindow2.showAsDropDown(view);
                }
                break;
        }


    }


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
    public void initData(){

        addItem(R.drawable.meituan_image1,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");
        addItem(R.drawable.meituan_image2,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image3,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image4,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image5,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image6,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image7,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

        addItem(R.drawable.meituan_image8,R.drawable.ic_global_list_lable_queue,R.drawable.ic_global_list_lable_voucher,R.drawable.ic_global_list_lable_queue,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,R.drawable.movie_detail_score_start_all,"十二买进台","104评价","保健/按摩","宽窄巷子","4.9分(65)","4.9分(65)");

    }

    public void addItem(int imag1, int imag2,int imag3,int imag4,
                        int imag5,int imag6,int imag7,int imag8,int imag9,String title, String content1, String content2, String content3, String content4,String content5){
        MessageBean  messageBean = new  MessageBean(imag1,imag2, imag3, imag4,
                imag5,imag6,imag7,imag8,imag9,title,content1, content2,content3,content4,content5);
        messageBean.setImag1(imag1);
        messageBean.setImag2(imag2);
        messageBean.setImag3(imag3);
        messageBean.setImag4(imag4);
        messageBean.setImag5(imag5);
        messageBean.setImag6(imag6);
        messageBean.setImag7(imag7);
        messageBean.setImag8(imag8);
        messageBean.setImag9(imag9);
        messageBean.setTitle(title);
        messageBean.setContent1(content1);
        messageBean.setContent2(content2);
        messageBean.setContent3(content3);
        messageBean.setContent4(content4);
        messageBean.setContent5(content5);
        list2.add(messageBean);
    }



    public class MerchantAdapter extends BaseAdapter {
        List<MessageBean> list = new ArrayList<>();
        LayoutInflater layoutInflater;

        public MerchantAdapter(Context context){

            layoutInflater = LayoutInflater.from(context);

        }
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            Holiview holdivew;
            if(view == null){
                view = layoutInflater.inflate(R.layout.activity_demo6_layout,null);

                ImageView imag = (ImageView) view.findViewById(R.id.shangjia_relativelayout_one_image);
                ImageView imag1 = (ImageView) view.findViewById(R.id.linearlayout_one_image_one);
                ImageView imag2 = (ImageView) view.findViewById(R.id.linearlayout_one_image_two);
                ImageView imag3 = (ImageView) view.findViewById(R.id.linearlayout_one_image_three);
                ImageView imagview1 = (ImageView) view.findViewById(R.id.linearlayout_two_image_one);
                ImageView imagview2 = (ImageView) view.findViewById(R.id.linearlayout_two_image_two);
                ImageView imagview3 = (ImageView) view.findViewById(R.id.linearlayout_two_image_three);
                ImageView imagview4 = (ImageView) view.findViewById(R.id.linearlayout_two_image_four);
                ImageView imagview5 = (ImageView) view.findViewById(R.id.linearlayout_two_image_five);

                TextView titletxt = (TextView) view.findViewById(R.id.linearlayout_one_textview);
                TextView contenttxt1 = (TextView) view.findViewById(R.id.linearlayout_two_textview);
                TextView contenttxt2 = (TextView) view.findViewById(R.id.textview_one);
                TextView contenttxt3 = (TextView) view.findViewById(R.id.textview_two);
                TextView contenttxt4 = (TextView) view.findViewById(R.id.linearlayout_three_textview_one);
                TextView contenttxt5 = (TextView) view.findViewById(R.id.linearlayout_three_textview_two);

                holdivew = new Holiview();
                holdivew.imag = imag;
                holdivew.imag1 = imag1;
                holdivew.imag2 = imag2;
                holdivew.imag3 = imag3;
                holdivew.imagview1 = imagview1;
                holdivew.imagview2 = imagview2;
                holdivew.imagview3 = imagview3;
                holdivew.imagview4 = imagview4;
                holdivew.imagview5 = imagview5;


                holdivew.titletxt = titletxt;
                holdivew.contenttxt1 = contenttxt1;
                holdivew.contenttxt2 = contenttxt2;
                holdivew.contenttxt3 = contenttxt3;
                holdivew.contenttxt4 = contenttxt4;
                holdivew.contenttxt5 = contenttxt5;


                view.setTag(holdivew);
            }
            holdivew = (Holiview) view.getTag();

            MessageBean  messageBean = (MessageBean) getItem(i);

            int imag1 = messageBean.getImag1();
            int imag2 = messageBean.getImag2();
            int imag3 = messageBean.getImag3();
            int imag4 = messageBean.getImag4();
            int imag5 = messageBean.getImag5();
            int imag6 = messageBean.getImag6();
            int imag7 = messageBean.getImag7();
            int imag8 = messageBean.getImag8();
            int imag9 = messageBean.getImag9();

            String title =  messageBean.getTitle();
            String content1  =  messageBean.getContent1();
            String content2 = messageBean.getContent2();
            String content3 = messageBean.getContent3();
            String content4  = messageBean.getContent4();
            String content5  = messageBean.getContent5();



            holdivew.imag.setImageResource(imag1);
            holdivew.imag1.setImageResource(imag2);
            holdivew.imag2.setImageResource(imag3);
            holdivew.imag3.setImageResource(imag4);
            holdivew.imagview1.setImageResource(imag5);
            holdivew.imagview2.setImageResource(imag6);
            holdivew.imagview3.setImageResource(imag7);
            holdivew.imagview4.setImageResource(imag8);
            holdivew.imagview5.setImageResource(imag9);

            holdivew.titletxt.setText(title);
            holdivew.contenttxt1.setText(content1);
            holdivew.contenttxt2.setText(content2);
            holdivew.contenttxt3.setText(content3);
            holdivew.contenttxt4.setText(content4);
            holdivew.contenttxt5.setText(content5);
            return view;
        }


        public class Holiview{
            ImageView imag;
            ImageView imag1;
            ImageView imag2;
            ImageView imag3;
            ImageView imagview1;
            ImageView imagview2;
            ImageView imagview3;
            ImageView imagview4;
            ImageView imagview5;

            TextView titletxt;
            TextView contenttxt1;
            TextView contenttxt2;
            TextView contenttxt3;
            TextView contenttxt4;
            TextView contenttxt5;
        }


        public void setList(List<MessageBean> list) {
            this.list = list;
        }
    }
    public class MessageBean{



        int imag1;
        int imag2;
        int imag3;
        int imag4;
        int imag5;
        int imag6;
        int imag7;
        int imag8;
        int imag9;


        String title;
        String content1;
        String content2;
        String content3;
        String content4;
        String content5;

//        public int getPic1() {
//            return pic1;
//        }
//
//        public void setPic1(int pic1) {
//            this.pic1 = pic1;
//        }
//
//        public int getPic2() {
//            return pic2;
//        }
//
//        public void setPic2(int pic2) {
//            this.pic2 = pic2;
//        }
//
//        public int getPic3() {
//            return pic3;
//        }
//
//        public void setPic3(int pic3) {
//            this.pic3 = pic3;
//        }
//
//        public int getPic4() {
//            return pic4;
//        }
//
//        public void setPic4(int pic4) {
//            this.pic4 = pic4;
//        }
//
//        public int getPic5() {
//            return pic5;
//        }
//
//        public void setPic5(int pic5) {
//            this.pic5 = pic5;
//        }

        public int getImag1() {
            return imag1;
        }

        public void setImag1(int imag1) {
            this.imag1 = imag1;
        }

        public int getImag2() {
            return imag2;
        }

        public void setImag2(int imag2) {
            this.imag2 = imag2;
        }

        public int getImag3() {
            return imag3;
        }

        public void setImag3(int imag3) {
            this.imag3 = imag3;
        }

        public int getImag4() {
            return imag4;
        }

        public void setImag4(int imag4) {
            this.imag4 = imag4;
        }

        public int getImag5() {
            return imag5;
        }

        public void setImag5(int imag5) {
            this.imag5 = imag5;
        }

        public int getImag6() {
            return imag6;
        }

        public void setImag6(int imag6) {
            this.imag6 = imag6;
        }

        public int getImag7() {
            return imag7;
        }

        public void setImag7(int imag7) {
            this.imag7 = imag7;
        }

        public int getImag8() {
            return imag8;
        }

        public void setImag8(int imag8) {
            this.imag8 = imag8;
        }

        public int getImag9() {
            return imag9;
        }

        public void setImag9(int imag9) {
            this.imag9 = imag9;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent1() {
            return content1;
        }

        public void setContent1(String content1) {
            this.content1 = content1;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }

        public String getContent3() {
            return content3;
        }

        public void setContent3(String content3) {
            this.content3 = content3;
        }

        public String getContent5() {
            return content5;
        }

        public void setContent5(String content5) {
            this.content5 = content5;
        }

        public String getContent4() {
            return content4;
        }

        public void setContent4(String content4) {
            this.content4 = content4;
        }

        public MessageBean(int imag1, int imag2,int imag3,int imag4,
                           int imag5,int imag6,int imag7,int imag8,int imag9,String title, String content1, String content2, String content3, String content4,String content5){

//            this.pic1 = pic1;
//            this.pic2 = pic2;
//            this.pic3 = pic3;
//            this.pic4 = pic4;
//            this.pic5 = pic5;

            this.imag1 =imag1;
            this.imag2 =imag2;
            this.imag3 =imag3;
            this.imag4 =imag4;
            this.imag5 =imag5;
            this.imag6 =imag6;
            this.imag7 =imag7;
            this.imag8 =imag8;
            this.imag9 =imag9;

            this.title = title;
            this.content1 = content1;
            this.content2 = content2;
            this.content3 = content3;
            this.content4 = content4;
            this.content5 = content5;
        }
    }
    public void initData2(){

        addData("电影",R.drawable.travel__ic_category_aerial);
        addData("美食",R.drawable.travel__ic_category_aquarium);
        addData("摄影写真",R.drawable.travel__ic_category_around);
        addData("酒店",R.drawable.travel__ic_category_car);
        addData("休闲娱乐",R.drawable.travel__ic_category_china);
        addData("汽车服务",R.drawable.travel__ic_category_climb);
        addData("生活服务",R.drawable.travel__ic_category_cs);
        addData("丽人",R.drawable.travel__ic_category_drifting);
        addData("休闲娱乐",R.drawable.travel__ic_category_china);
        addData("汽车服务",R.drawable.travel__ic_category_climb);
        addData("生活服务",R.drawable.travel__ic_category_cs);
        addData("丽人",R.drawable.travel__ic_category_drifting);


    }
    public void initData3(){

        addData2("推荐商圈",R.drawable.travel__ic_category_aerial);
        addData2("新津县",R.drawable.travel__ic_category_aquarium);
        addData2("锦江区",R.drawable.travel__ic_category_around);
        addData2("金牛区",R.drawable.travel__ic_category_car);
        addData2("龙泉驿",R.drawable.travel__ic_category_china);
        addData2("武侯区",R.drawable.travel__ic_category_climb);
        addData2("青白江区",R.drawable.travel__ic_category_cs);
        addData2("新都县",R.drawable.travel__ic_category_drifting);
        addData2("龙泉驿",R.drawable.travel__ic_category_china);
        addData2("武侯区",R.drawable.travel__ic_category_climb);
        addData2("青白江区",R.drawable.travel__ic_category_cs);
        addData2("新都县",R.drawable.travel__ic_category_drifting);
    }


    public void addData(String name,int pic){

        Student studnent = new Student(name,pic);
        listsssss.add(studnent);
    }
    public void addData2(String name,int pic){

        Student2 studnent2 = new Student2(name,pic);
        listsssss2.add(studnent2);
    }

    public class Student  {
        String name;
        int pic;
        public Student(String name,int pic){
            this.name = name;

            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }
    }
    public class Student2 {
        String name;
        int pic;

        public Student2(String name, int pic) {
            this.name = name;

            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }
    }
    class MyStudentAdapter extends BaseAdapter {

        public List<Student> list = new ArrayList<>();
        public LayoutInflater layoutInflater;

        public MyStudentAdapter(Context context, List<Student> list) {
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            HoldView holdView;
            if (view == null) {
                // todo 一级优化  优化View不被重复解析
                view = layoutInflater.inflate(R.layout.activity_demo__simple_layout, null);

                ImageView iconimag = (ImageView) view.findViewById(R.id.simple_demo_imageview);
                TextView titletxt = (TextView) view.findViewById(R.id.simple_demo_textviewtitle);

                // todo 二级优化  优化view控件不被重复加载
                holdView = new HoldView();
                holdView.iconimg = iconimag;
                holdView.titletxt = titletxt;

                view.setTag(holdView);// todo setTag里面放的是一个对象，为了view控件不被重复加载，需要再定义一个类放控件
            }

            holdView = (HoldView) view.getTag();
            // todo 从View对象中获取控件实例
            Student item = (Student) getItem(i);

            int icon = item.getPic();
            String title = item.getName();


            holdView.iconimg.setImageResource(icon);
            holdView.titletxt.setText(title);

            return view;
        }
    }
    public class HoldView{
        ImageView iconimg;
        TextView titletxt;

    }
    class MyStudentAdapter2 extends BaseAdapter {

        public List<Student2> list = new ArrayList<>();
        public LayoutInflater layoutInflater;

        public MyStudentAdapter2(Context context, List<Student2> list) {
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            HoldView2 holdView2;
            if (view == null) {
                // todo 一级优化  优化View不被重复解析
                view = layoutInflater.inflate(R.layout.activity_demo_simple2_layout, null);

                ImageView iconimag = (ImageView) view.findViewById(R.id.simple_demo_imageview2);
                TextView titletxt = (TextView) view.findViewById(R.id.simple_demo_textviewtitle2);


                // todo 二级优化  优化view控件不被重复加载
                holdView2 = new HoldView2();
                holdView2.iconimg = iconimag;
                holdView2.titletxt = titletxt;

                view.setTag(holdView2);// todo setTag里面放的是一个对象，为了view控件不被重复加载，需要再定义一个类放控件
            }

            holdView2 = (HoldView2)view.getTag();
            // todo 从View对象中获取控件实例
            Student2 item = (Student2) getItem(i);

            int icon = item.getPic();
            String title = item.getName();


            holdView2.iconimg.setImageResource(icon);
            holdView2.titletxt.setText(title);

            return view;
        }
    }
    public class HoldView2{
        ImageView iconimg;
        TextView titletxt;

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
