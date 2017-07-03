package com.example.administrator.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class MyCountAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> mList;
    private long time;

    public MyCountAdapter(Context context, ArrayList<String> listData) {
        this.mContext = context;
        this.mList = listData;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_adapter_listview, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Long date_finish = DateFormatUtils.parseStr2Date(mList.get(position),"yyyy-MM-dd HH:mm:ss").getTime();
        if (position == 1) {
            Log.e("TAG","倒计时："+(date_finish-time));
        }
        Long date = date_finish-time;
        if (date>0) {
            holder.tv.setText(DateFormatUtils.getDateTime((date_finish-time),"mm:ss"));
        }else{
            holder.tv.setText("00:00");
        }

        return convertView;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private class ViewHolder{
        private TextView tv;
    }

}
