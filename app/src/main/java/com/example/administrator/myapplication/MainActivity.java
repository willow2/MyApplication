package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.timeview.ScreenInfo;
import com.example.administrator.myapplication.timeview.WheelHour;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private LayoutInflater inflater;
    private View view;
    private TextView title;
    private WheelHour wheelHour;
    int hour1, min1, hour, min;
    private TextView diss;
    private TextView confirm;
    private PopupWindow mPopupWindowDialog;
    private TextView tv;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar c1,c2;
    private TimeCount time;
    private int chaoshitime;
    private String timefromServer;
    private long countdownTime;
    private String timefromOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        getTimeDuring();
//        Date d = new Date("2015-01-07 11:15:15");
//        long t = d.getTime();
//        time = new TimeCount(t*1000, 1000);
//        time.start();
//        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("mm:ss");
//        String    date    =    sDateFormat.format(new    java.util.Date());
//        tv.setText( sDateFormat.format(time.getMillisUntilFinished()));
//        c1= Calendar.getInstance();
//        c2=Calendar.getInstance();
//        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                view = inflater.inflate(R.layout.hour, null);
//                title = (TextView)view.findViewById(R.id.time_dialog_title);
//                title.setText("请设置开始时间");
//                setPopupWindowDialog();
//
//                ScreenInfo screenInfo = new ScreenInfo(MainActivity.this);
//                wheelHour = new WheelHour(view, 0);
//                wheelHour.screenheight = screenInfo.getHeight();
//                wheelHour.initDateTimePicker(hour, min, hour1, min1);
//
//                if (mPopupWindowDialog != null) {
//                    mPopupWindowDialog.showAtLocation(btn, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
//                }
//                startBtn();
//            }
//        });
    }

    private void getTimeDuring() {
        chaoshitime = 30 * 60 * 1000;//应该从服务器获取

        timefromServer = "2017-05-02 11:40:50";//应该从服务器获取
        timefromOrder = "2017-05-02 11:45:24";//订单时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date serverDate = df.parse(timefromServer);
            Date orderDate = df.parse(timefromOrder);
            long duringTime = orderDate.getTime() - serverDate.getTime();//计算当前时间和从服务器获取的订单生成时间的时间差
            countdownTime = duringTime;//计算倒计时的总时间

            handler.postDelayed(runnable, 1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }

}

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            countdownTime -= 1000;//倒计时总时间减1

            SimpleDateFormat minforamt = new SimpleDateFormat("mm:ss");

            String hms = minforamt.format(countdownTime);//格式化倒计时的总时间
            tv.setText(hms);
            handler.postDelayed(this, 1000);
        }
    };


    private void startBtn() {
        diss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mPopupWindowDialog != null
                        && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    //结束时间
                    c1.setTime(df.parse(wheelHour.getEndTime()));
                    //开始时间
                    c2.setTime(df.parse(wheelHour.getStartTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.err.println("格式错误");
                }
                int result=c1.compareTo(c2);
                if(result<0){
                    Toast.makeText(MainActivity.this,"上班时间要早于下班时间",Toast.LENGTH_SHORT).show();
                }else{
                    tv.setText(wheelHour.getStartTime()+"-"+wheelHour.getEndTime());
                    hour = wheelHour.getStartHour();
                    hour1 = wheelHour.getEndHour();
                }
                if (mPopupWindowDialog != null
                        && mPopupWindowDialog.isShowing()) {
                    mPopupWindowDialog.dismiss();
                }
            }
        });

    }

    private void setPopupWindowDialog() {

        diss = (TextView)view.findViewById(R.id.sex_dialog_cancel);
        confirm = (TextView)view.findViewById(R.id.sex_dialog_album);
        mPopupWindowDialog = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindowDialog.setAnimationStyle(R.style.popupAnim);
        mPopupWindowDialog.setFocusable(true);
        mPopupWindowDialog.update();
        mPopupWindowDialog.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindowDialog.setOutsideTouchable(true);

    }
}
//
//listData = new ArrayList<String>();
//        listData.add("2017-05-02 14:30:01");
//        listData.add("2017-05-02 14:27:43");
//        listData.add("2017-05-02 14:33:12");
//        listData.add("2017-05-02 14:36:24");
//        listData.add("2017-05-02 14:34:25");
//        listData.add("2017-05-02 14:37:16");
//        listData.add("2017-05-02 14:36:38");
//        listData.add("2017-05-02 14:32:19");
//        listData.add("2017-05-02 14:33:41");
//        listData.add("2017-05-02 14:36:10");
//        listData.add("2017-05-02 14:37:34");
//        listData.add("2017-05-02 14:38:17");
//        listData.add("2017-05-02 14:36:22");
//        listData.add("2017-05-02 14:35:46");
//        listData.add("2017-05-02 14:34:17");
//        listData.add("2017-05-02 14:33:25");
//        listData.add("2017-05-02 14:32:37");
//        listData.add("2017-05-02 14:35:10");
//        listData.add("2017-05-02 14:36:39");
//        listData.add("2017-05-02 14:37:34");
//        listData.add("2017-05-02 14:39:58");
//        listData.add("2017-05-02 14:41:21");
//        listData.add("2017-05-02 14:42:32");
//        Time = DateFormatUtils.parseStr2Date("2017-05-02 14:27:35", "yyyy-MM-dd HH:mm:ss").getTime();
//        myCountAdapter = new MyCountAdapter(Main2Activity.this,listData);
//        lv.setAdapter(myCountAdapter);
//        handler_timeCurrent.sendEmptyMessageDelayed(0,1000);