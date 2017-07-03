package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class TimeTextView extends TextView{

    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
    long Time;
    private boolean run = true; // 是否启动了

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }

    protected Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (run) {
                        long mTime = Time;
                        if (mTime > 0) {
                            String day = "";
                            TimeTextView.this.setText(Time+"s");
                            Time = Time - 1000;
                            handler.sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        TimeTextView.this.setVisibility(View.GONE);
                    }

                    break;
                default:
                    break;
            }
        };
    };

    public TimeTextView(Context context) {
        super(context);
    };

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("NewApi")
    public void setTimes(long endTime, long mT) {
        // 标示已经启动
        Date date = new Date();
        long t2 = date.getTime();
        Time = endTime - mT;
        date = null;

        if (Time > 0) {
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        } else {
            TimeTextView.this.setVisibility(View.GONE);
        }
    }

    public void stop() {
        run = false;
    }

}
