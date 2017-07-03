package com.example.administrator.myapplication;

import android.os.CountDownTimer;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class TimeCount extends CountDownTimer {

    private long millisUntilFinished;

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
    }
    @Override
    public void onFinish() {// 计时完毕时触发

    }
    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        this.millisUntilFinished = millisUntilFinished;

    }

    public long getMillisUntilFinished(){
        return millisUntilFinished;
    }
}
