package com.example.scxh.mymeituan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MTActivity extends AppCompatActivity implements View.OnClickListener{

    public  TextView textViewforresult,timeone,timetwo,timethree;
    public ViewPager mviewpager;
    public ImageView mimageview1,mimageview2,mimageview3,imageviewpay,imggeviewlistener;
    public GridView mGridView1,mGridView2,mGridView3;

    ListView mListView,popupListView;
    List<MessageBean> list2 = new ArrayList<>();
    public List<Student> listsssss = new ArrayList<>();

    ArrayList<Pict> listpict1 = new ArrayList<>();//// TODO: 2016/6/9 必须是专用 ，混用会造成重复添加
    ArrayList<Pict> listpict2 ;
    ArrayList<Pict> listpict3 ;

    boolean back = false;
    PopupWindow popupWindow;
    int secend;
    int hour=2;
    int minute=1;
    public Handler handler = new Handler();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt2_layout);// TODO: 2016/6/9 这里的xml是总布局

        // TODO: 2016/6/23 实例化各个选项
        textViewforresult = (TextView) findViewById(R.id.myactionbar_txt);
        textViewforresult.setOnClickListener(this);

        timeone = (TextView) findViewById(R.id.time_textone);
        timetwo = (TextView) findViewById(R.id.time_texttwo);
        timethree = (TextView) findViewById(R.id.time_textthree);


        imageviewpay = (ImageView) findViewById(R.id.myactionbar_pay);
        imageviewpay.setOnClickListener(this);//记住监听啊

        imggeviewlistener = (ImageView) findViewById(R.id.mta_listener);
        imggeviewlistener.setOnClickListener(this);

        mviewpager = (ViewPager) findViewById(R.id.viewpager_mt_viewpager);// TODO: 2016/6/9 总布局里的viewpager
        mimageview1 = (ImageView) findViewById(R.id.adapter_mt_imageview1);// TODO: 2016/6/9 总布局viewpager里面的imageview
        mimageview2 = (ImageView) findViewById(R.id.adapter_mt_imageview2);
        mimageview3 = (ImageView) findViewById(R.id.adapter_mt_imageview3);


        SelectMore();

        PayMonney();

        TimeCount();

        GuessYouLike();


        // TODO: 2016/6/9 viewpager对应的屏幕滑动， onPageScrolled正在滑动，可以做动画  onPageSelected滑动到具体某一面时
        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // TODO: 2016/6/9 position 是每一页的编号，第一页是 0
            public void onPageSelected(int position) {
                listpict2 = new ArrayList<>();
                listpict3 = new ArrayList<>();
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

                        mGridView2 = (GridView) findViewById(R.id.gridview2);
                        getPicture2();
                        GridViewAdapter adapter3 = new GridViewAdapter(MTActivity.this);
                        adapter3.setList(listpict2);
                        Logs.e("kkkkkkkkkkk"+listpict2);
                        mGridView2.setAdapter(adapter3);

                        break;
                    case 2:
                        mimageview1.setImageResource(R.drawable.page_now);
                        mimageview2.setImageResource(R.drawable.page_now);
                        mimageview3.setImageResource(R.drawable.page);

                        mGridView3 = (GridView) findViewById(R.id.gridview3);
                        getPicture3();
                        GridViewAdapter adapter4 = new GridViewAdapter(MTActivity.this);
                        adapter4.setList(listpict3);
                        Logs.e("llllllll"+listpict3);
                        mGridView3.setAdapter(adapter4);

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // TODO: 2016/6/23 onCreate()的  }；
    }

    public void SelectMore(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.activity_demo_meituan_layout,null);// TODO: 2016/6/9 viewpager对应的xml布局
        View view2 = layoutInflater.inflate(R.layout.activity_demo_meituan2_layout,null);
        View view3 = layoutInflater.inflate(R.layout.activity_demo_meituan3_layout,null);

        List<View> list = new ArrayList<>();// TODO: 2016/6/9 viewpager的每一个activity的添加
        list.add(view1);
        list.add(view2);
        list.add(view3);

        ViewAdapter adapter = new ViewAdapter(list);// TODO: 2016/6/9 ViewAdapter是viewpager 的自定义
        mviewpager.setAdapter(adapter);

        // TODO: 2016/6/14  加view1是因为(R.layout.activity_demo_meituan_layout)已经解析了
        // todo 直接findViewById(R.id.gridview1)会在里面寻找，就会报空指针错
        mGridView1 = (GridView)view1.findViewById(R.id.gridview1);
        getPicture1();// TODO: 2016/6/9 得到网格的每一项
        GridViewAdapter adapter2 = new GridViewAdapter(MTActivity.this);// TODO: 2016/6/9 自定义的网格控件
        adapter2.setList(listpict1);
        Logs.e("jjjjjjjj"+listpict1);
        mGridView1.setAdapter(adapter2);

    }


    public void GuessYouLike(){

        mListView = (ListView) findViewById(R.id.viewpager_mt_listview); // TODO: 2016/6/9 总布局里面的listview
        initData();// TODO: 2016/6/9 向listview 里面添加数据
        Adapter_main myStudentAdapter = new Adapter_main(this);// TODO: 2016/6/9 自定义的listview 的子项布局
        myStudentAdapter.setList(list2);
        mListView.setAdapter(myStudentAdapter);//注册

    }

    public void PayMonney(){
        // TODO: 2016/6/22 开始进行pay付款码的解析

        LayoutInflater inflater2 = LayoutInflater.from(this);
        View popupContentView = inflater2.inflate(R.layout.activity_mypay_layout,null);
        popupListView = (ListView) popupContentView.findViewById(R.id.mypaylistview);
        initData2();
        MyStudentAdapter payAdapter = new MyStudentAdapter(this,listsssss);
        popupListView.setAdapter(payAdapter);

        // TODO: 2016/6/12 PopupWindow 是系统准备好的实例化后直接用
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupContentView);
        popupWindow.setWidth(255);// TODO: 2016/6/12  设定弹出框的宽高
        popupWindow.setHeight(200);
        // TODO: 2016/6/12 以下属性保证点击其它区域，弹出的内容可以自行回收
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);



    }

    // TODO: 2016/6/22 左上角的地址传参 
    public void onClick(View view) {
        int id = view.getId();
        Logs.e("onclick"+id);
        switch (id){
            case R.id.myactionbar_txt:
                CitySelect();
                break;
            case R.id.myactionbar_pay:
                MoneyPay(view);
                break;
            case R.id.mta_listener:
                Intent intent = new Intent(MTActivity.this, Login_Activity.class);
                startActivity(intent);
                break;



        }

    }
    public void MoneyPay(View view){
        Logs.e("moneypayKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }else{
            popupWindow.showAsDropDown(view);
        }

    }
    public void CitySelect(){

        String city = textViewforresult.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("CITY",city);//// TODO: 2016/6/6  键值对 bundle.putString

        Intent intent=new Intent(this,City_Activity.class);
        intent.putExtra("BUNDLE",bundle);

        int requestCode2=202;
        startActivityForResult(intent,requestCode2);//// TODO: 2016/6/6 回参（intent,请求

    }

    // TODO: 2016/6/22 回传值 
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        switch(resultCode){//TODO 返回值 resultCode 用来决定使用case xxx
            case 1:
                String msg1 = intent.getStringExtra("ONE");//TODO MESSAGE是回调的信息
                textViewforresult.setText(msg1);
                break;
            case 2:
                String msg2 = intent.getStringExtra("TWO");//TODO MESSAGE是回调的信息
                textViewforresult.setText(msg2);
                break;
            case 3:
                String msg3 = intent.getStringExtra("THREE");//TODO MESSAGE是回调的信息
                textViewforresult.setText(msg3);
                break;
            case 4:
                String msg4 = intent.getStringExtra("FOUR");//TODO MESSAGE是回调的信息
                textViewforresult.setText(msg4);
                break;
            case 5:
                String msg5 = intent.getStringExtra("FIVE");//TODO MESSAGE是回调的信息
                textViewforresult.setText(msg5);
                break;
        }

    }
    public class ViewAdapter extends PagerAdapter {
        List<View> list = new ArrayList<>();
        public ViewAdapter(List<View> list){
            this.list = list;
        }

        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        public Object instantiateItem(ViewGroup container, int position) {
            View view = list.get(position);
            container.addView(view);
            return view;
        }
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = list.get(position);
            container.removeView(view);
        }
    }


    public class GridViewAdapter extends BaseAdapter {
        List<Pict> list = new ArrayList<>();
        LayoutInflater layoutInflater;

        public GridViewAdapter(Context context) {
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
        public void setList(ArrayList list) {
            this.list = list;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            HoldView holdView;
            if (view == null) {
                view = layoutInflater.inflate(R.layout.activity_demo__grid_view_layout, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.gridView_imageview);
                TextView textView = (TextView) view.findViewById(R.id.gridView_textview);

                holdView = new HoldView();
                holdView.imageView = imageView;
                holdView.textView = textView;

                view.setTag(holdView);
            }
            holdView = (HoldView) view.getTag();
            Pict pict = (Pict) getItem(i);
            int pic = pict.getPic();
            String str = pict.getTxt();


            holdView.imageView.setImageResource(pic);
            holdView.textView.setText(str);


            return view;
        }
    }
    public class HoldView {
        ImageView imageView;
        TextView textView;
    }

    public class Pict {
        int pic;
        String txt;

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public void AddData1(int id,String string){
//        listpict1 = new ArrayList<>()  TODO: 2016/6/9  不能在这里实例化，否则方法getPicture每调用一次，实例化一次，造成只添加最后一个
//        ArrayList<Pict> listpict1 = new ArrayList<>();
        Pict pict = new Pict();
        pict.setPic(id);
        pict.setTxt(string);
        listpict1.add(pict);

    }
    public void getPicture1(){
        AddData1(R.drawable.ic_category_1,"美食");
        AddData1(R.drawable.ic_category_2,"电影");
        AddData1(R.drawable.ic_category_3,"酒店");
        AddData1(R.drawable.ic_category_4,"休闲娱乐");
        AddData1(R.drawable.ic_category_5,"自助餐");
        AddData1(R.drawable.ic_category_6,"KTV");
        AddData1(R.drawable.ic_category_7,"火车票机票");
        AddData1(R.drawable.ic_category_8,"丽人");
        AddData1(R.drawable.ic_category_10,"周边游");
        AddData1(R.drawable.ic_category_11,"外卖");
    }

    public void AddData2(int id,String string){

        Pict pict = new Pict();
        pict.setPic(id);
        pict.setTxt(string);
        listpict2.add(pict);

    }
    public void getPicture2(){
        AddData2(R.drawable.ic_category_4,"足疗按摩");
        AddData2(R.drawable.ic_category_6,"美发");
        AddData2(R.drawable.ic_category_2,"小吃快餐");
        AddData2(R.drawable.ic_category_3,"火锅");
        AddData2(R.drawable.ic_category_4,"甜点饮品");
        AddData2(R.drawable.ic_category_5,"今日新单");
        AddData2(R.drawable.ic_category_6,"生日蛋糕");
        AddData2(R.drawable.ic_category_7,"洗浴/汗蒸");
        AddData2(R.drawable.ic_category_8,"生活服务");
        AddData2(R.drawable.ic_category_25,"景点");

    }
    public void AddData3(int id,String string){

        Pict pict = new Pict();
        pict.setPic(id);
        pict.setTxt(string);
        listpict3.add(pict);

    }
    public void getPicture3(){
        AddData3(R.drawable.ic_category_8,"汽车服务");
        AddData3(R.drawable.ic_category_10,"美甲美瞳");
        AddData3(R.drawable.ic_category_11,"母婴亲子");
        AddData3(R.drawable.ic_category_12,"烧烤烤肉");
        AddData3(R.drawable.ic_category_13,"婚纱摄影");
        AddData3(R.drawable.ic_category_14,"学习培训");
        AddData3(R.drawable.ic_category_15,"家装");
        AddData3(R.drawable.ic_category_16,"结婚");
        AddData3(R.drawable.ic_category_23,"购物");
        AddData3(R.drawable.ic_category_25,"全部分类");

    }
    public void initData2(){

        addData("扫一扫",R.drawable.actionbar_index_pop_scan_item_scan);
        addData("付款码",R.drawable.actionbar_index_pop_scan_item_pay_code);


    }
    public void addData(String name,int pic){

        Student studnent = new Student(name,pic);
        listsssss.add(studnent);
    }

    public SpannableString Strike(String string) {
        int n = string.length();
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new StrikethroughSpan(), 0, n,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, n,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }
    public void initData(){

        addItem(R.drawable.meituan_image2,R.drawable.ic_label_deal_second,"港台美食汇","【天府广场】雪花绵绵冰4选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image3,R.drawable.ic_label_nobooking,"烧烤美食大全","【太平路】单人自助烧烤，流连忘返的美食","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image4,R.drawable.ic_label_onlinebooking,"新石器烤肉","【村西路】代金券1张，全场通用，可叠加使用","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image5,R.drawable.ic_label_deal_second,"水果捞","【府青路】水果特色餐饮3选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image6,R.drawable.ic_label_nobooking,"港台美食汇","【天府广场】雪花绵绵冰4选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image8,R.drawable.ic_label_onlinebooking,"港台美食汇","【天府广场】雪花绵绵冰4选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image6,R.drawable.ic_label_onlinebooking,"港台美食汇","【天府广场】雪花绵绵冰4选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
        addItem(R.drawable.meituan_image7,R.drawable.ic_label_onlinebooking,"港台美食汇","【天府广场】雪花绵绵冰4选1，提供免费wifi","9.9元",Strike("25元"),"4.9分(65)");
    }
    public void addItem(int pic, int imag, String title, String content1, String content2, SpannableString spannableString, String content4){
        MessageBean  messageBean = new MessageBean(pic,imag,title, content1,content2,spannableString,content4);
        list2.add(messageBean);
    }

    public class Adapter_main extends BaseAdapter {
        List<MessageBean> list = new ArrayList<>();
        LayoutInflater layoutInflater;

        public Adapter_main(Context context){

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
                view = layoutInflater.inflate(R.layout.activity_mt_listview,null);
                RelativeLayout iconimag = (RelativeLayout) view.findViewById(R.id.demo_imageview);
                ImageView imag = (ImageView) view.findViewById(R.id.demo_imageview_root);
                TextView titletxt = (TextView) view.findViewById(R.id.demo_textviewtitle);
                TextView contenttxt1 = (TextView) view.findViewById(R.id.demo_textviewcontent);
                TextView contenttxt2 = (TextView) view.findViewById(R.id.demo_textviewcontent2);
                TextView contenttxt3 = (TextView) view.findViewById(R.id.demo_textviewcontent3);
                TextView contenttxt4 = (TextView) view.findViewById(R.id.demo_textviewcontent4);

                holdivew = new Holiview();
                holdivew.iconimg = iconimag;
                holdivew.imag = imag;
                holdivew.titletxt = titletxt;
                holdivew.contenttxt1 = contenttxt1;
                holdivew.contenttxt2 = contenttxt2;
                holdivew.spannableString = contenttxt3;
                holdivew.contenttxt4 = contenttxt4;
                view.setTag(holdivew);
            }
            holdivew = (Holiview) view.getTag();

            MessageBean  messageBean = (MessageBean) getItem(i);

            int icon = messageBean.getPic();
            int imag = messageBean.getImag();
            String title =  messageBean.getTitle();
            String content1  =  messageBean.getContent1();
            String content2 = messageBean.getContent2();
            SpannableString spannableString  = messageBean.getSpannableString();
            String content4  = messageBean.getContent4();

            holdivew.iconimg.setBackgroundResource(icon);//
            holdivew.imag.setImageResource(imag);
            holdivew.titletxt.setText(title);
            holdivew.contenttxt1.setText(content1);
            holdivew.contenttxt2.setText(content2);
            holdivew.spannableString.setText(spannableString);
            holdivew.contenttxt4.setText(content4);

            return view;
        }


        public class Holiview{
            RelativeLayout iconimg;
            ImageView imag;
            TextView titletxt;
            TextView contenttxt1;
            TextView contenttxt2;
            TextView spannableString;
            TextView contenttxt4;
        }


        public void setList(List<MessageBean> list) {
            this.list = list;
        }
    }
    public class MessageBean{

        int pic ;
        int imag;
        String title;
        String content1;
        String content2;
        SpannableString spannableString;
        String content4;

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public int getImag() {
            return imag;
        }

        public void setImag(int imag) {
            this.imag = imag;
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

        public SpannableString getSpannableString() {
            return spannableString;
        }

        public void setSpannableString(SpannableString spannableString) {
            this.spannableString = spannableString;
        }

        public String getContent4() {
            return content4;
        }

        public void setContent4(String content4) {
            this.content4 = content4;
        }

        public MessageBean(int pic, int imag, String title, String content1, String content2, SpannableString spannableString, String content4){

            this.pic = pic;
            this.imag =imag;
            this.title = title;
            this.content1 = content1;
            this.content2 = content2;
            this.spannableString = spannableString;
            this.content4 = content4;
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
            HoldViewpay holdViewpay;
            if (view == null) {
                // todo 一级优化  优化View不被重复解析
                view = layoutInflater.inflate(R.layout.activity_pay_layout, null);

                ImageView iconimag = (ImageView) view.findViewById(R.id.pay_imageview);
                TextView titletxt = (TextView) view.findViewById(R.id.pay_textviewtitle);

                // todo 二级优化  优化view控件不被重复加载
                holdViewpay = new HoldViewpay();
                holdViewpay.iconimg = iconimag;
                holdViewpay.titletxt = titletxt;

                view.setTag(holdViewpay);// todo setTag里面放的是一个对象，为了view控件不被重复加载，需要再定义一个类放控件
            }

            holdViewpay = (HoldViewpay) view.getTag();
            // todo 从View对象中获取控件实例
            Student item = (Student) getItem(i);

            int icon = item.getPic();
            String title = item.getName();


            holdViewpay.iconimg.setImageResource(icon);
            holdViewpay.titletxt.setText(title);

            return view;
        }
    }
    public class HoldViewpay{
        ImageView iconimg;
        TextView titletxt;

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
    public void TimeCount(){

        new Thread(new Runnable() {

            public void run() {
                boolean blog = true;
                while (blog) {
                    Logs.e("blog"+blog);
                    for (secend = 59; secend >= 0; secend--) {
                        SystemClock.sleep(1000);
                        handler.post(new Runnable() {
                            public void run() {
                                timethree.setText(secend + "");

                            }
                        });
                        if (secend == 0) {

                            if (minute == 0) {
                                if (secend == 0 & minute == 0 & hour == 0) {

                                    blog = false;
                                    Logs.e("blog"+blog);
                                    new Thread().interrupt();

                                }
                                hour--;
                                minute=60;
                                handler.post(new Runnable() {
                                    public void run() {
                                        timeone.setText(hour + "");

                                    }
                                });
                            }
                            minute--;
                            secend=60;
                            handler.post(new Runnable() {
                                public void run() {
                                    timetwo.setText(minute + "");

                                }
                            });


                        }
                    }
                }
            }
        }).start();

    }

}