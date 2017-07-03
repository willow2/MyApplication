package com.example.administrator.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.time.CountdownView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView lv;
    private MyCountAdapter myCountAdapter;
    private ArrayList<String> listData;
    private long Time;
    private List<ItemInfo> mDataList;
    private MyListAdapter mMyAdapter;
    private TextView tvClock;
    public static final String CLOCK_ACTION="com.jereh.Clock_Action";
    public static  int TIME=2*60*60*1000;//倒计时2个小时
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Time = DateFormatUtils.parseStr2Date("2017-05-03 17:30:01", "yyyy-MM-dd HH:mm:ss").getTime();
        initData();
        lv = (ListView)findViewById(R.id.lv_list);
        lv.setAdapter(mMyAdapter = new MyListAdapter(this, mDataList));
    }

    private void initData() {

        ArrayList<String> listData = new ArrayList<String>();
        listData.add("2017-05-03 17:30:01");
        listData.add("2017-05-03 17:27:43");
        listData.add("2017-05-03 17:33:12");
        listData.add("2017-05-03 17:36:24");
        listData.add("2017-05-03 17:34:25");
        listData.add("2017-05-03 17:37:16");
        listData.add("2017-05-03 17:36:38");
        listData.add("2017-05-03 17:32:19");
        listData.add("2017-05-03 17:33:41");
        listData.add("2017-05-03 17:36:10");
        listData.add("2017-05-03 17:37:34");
        listData.add("2017-05-03 17:38:17");
        listData.add("2017-05-03 17:36:22");
        listData.add("2017-05-03 17:35:46");
        listData.add("2017-05-03 17:34:17");
        listData.add("2017-05-03 17:33:25");
        listData.add("2017-05-03 17:32:37");
        listData.add("2017-05-03 17:35:10");
        listData.add("2017-05-03 17:36:39");
        listData.add("2017-05-03 17:37:34");
        listData.add("2017-05-03 17:39:58");
        listData.add("2017-05-03 17:41:21");
        listData.add("2017-05-03 17:42:32");

        mDataList = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {
            mDataList.add(new ItemInfo(i, "ListView_测试标题_" + i, DateFormatUtils.parseStr2Date(listData.get(i).toString(), "yyyy-MM-dd HH:mm:ss").getTime(),Time));
        }

//        for (int i = 1; i < 25; i++) {
//
//        }
//
//        // 校对倒计时
        long curTime = System.currentTimeMillis();
        for (ItemInfo itemInfo : mDataList) {
            itemInfo.setEndTime((itemInfo.getCountdown()-DateFormatUtils.parseStr2Date("2017-05-03 17:30:01", "yyyy-MM-dd HH:mm:ss").getTime())+System.currentTimeMillis());
        }
    }

    static class MyListAdapter extends BaseAdapter {
        private Context mContext;
        private List<ItemInfo> mDatas;
        private boolean run = false;

        public MyListAdapter(Context context, List<ItemInfo> datas) {
            this.mContext = context;
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final MyViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
                holder = new MyViewHolder();
                holder.initView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (MyViewHolder) convertView.getTag();
            }

            ItemInfo curItemInfo = mDatas.get(position);
            holder.bindData(curItemInfo);

            dealWithLifeCycle(holder, position);


            return convertView;
        }

        /**
         * 以下两个接口代替 activity.onStart() 和 activity.onStop(), 控制 timer 的开关
         */
        private void dealWithLifeCycle(final MyViewHolder holder, final int position) {
            holder.getCvCountdownView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

                @Override
                public void onViewAttachedToWindow(View countdownView) {
                    int pos = position;
//                    Log.d("MyViewHolder", String.format("mCvCountdownView %s is attachedToWindow", pos));x
                    ItemInfo itemInfo = mDatas.get(pos);
                    holder.refreshTime(itemInfo.getEndTime()-System.currentTimeMillis());
                }

                @Override
                public void onViewDetachedFromWindow(View countdownView) {
//                    int pos = position;
//                    Log.d("MyViewHolder", String.format("mCvCountdownView %s is detachedFromWindow", pos));

                    holder.getCvCountdownView().stop();
                }
            });
        }

        class MyViewHolder {
            private TextView mTvTitle;
            private CountdownView mCvCountdownView;
            private ItemInfo mItemInfo;
            private TimeTextView mTvTime;
            public void initView(View convertView) {
                mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                mCvCountdownView = (CountdownView) convertView.findViewById(R.id.cv_countdownView);
                mTvTime = (TimeTextView) convertView.findViewById(R.id.tv_time);
            }

            public void bindData(ItemInfo itemInfo) {
                mItemInfo = itemInfo;
                mTvTitle.setText(itemInfo.getTitle());
                refreshTime(itemInfo.getEndTime()-System.currentTimeMillis());
            }

            public void refreshTime(long leftTime) {
                if (leftTime > 0) {
                    mCvCountdownView.start(leftTime);
                } else {
                    mCvCountdownView.stop();
                    mCvCountdownView.allShowZero();
                }
            }

            public ItemInfo getBean() {
                return mItemInfo;
            }

            public CountdownView getCvCountdownView() {
                return mCvCountdownView;
            }
        }
    }

    static class ItemInfo {
        private int id;
        private String title;
        private long countdown;
        /*
           根据服务器返回的countdown换算成手机对应的开奖时间 (毫秒)
           [正常情况最好由服务器返回countdown字段，然后客户端再校对成该手机对应的时间，不然误差很大]
         */
        private long endTime;

        private long startTime;

        public ItemInfo(int id, String title, long countdown, long startTime) {
            this.id = id;
            this.title = title;
            this.countdown = countdown;
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCountdown() {
            return countdown;
        }

        public void setCountdown(long countdown) {
            this.countdown = countdown;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }


}
