package com.example.administrator.myapplication.timeview;



import android.view.View;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WheelHour {

	private View view;
	private WheelView wv_hours;
	private WheelView wv_mins;
	private WheelView wv_hours1;
	private WheelView wv_mins1;
	public int screenheight;

	private int iTime;
	private ArrayList<Map<String, Object>> list ,min;
	private String get_hours,get_mins,get_hours1,get_mins1;

	private int startHour;
	private int startMin;

	public int getStartMin() {
		return startMin;
	}

	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMin() {
		return endMin;
	}

	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}

	private int endHour;
	private int endMin;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}


	public WheelHour(View view) {
		super();
		this.view = view;
		iTime = 0;
		setView(view);
	}

	public WheelHour(View view, int iTime) {
		super();
		this.view = view;
		this.iTime = iTime;
		list = new ArrayList<Map<String,Object>>();
		min = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 24; i+=2) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (i < 10) {
				map.put("text", "0"+i);
			}else{
				map.put("text", String.valueOf(i));
			}
			if (i == 0) {
				map.put("text","00");
				min.add(map);
			}
			list.add(map);
		}
		setView(view);
	}

	public void initDateTimePicker(int year, int month, int day) {
		this.initDateTimePicker(0, 0, 0, 0);
	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePicker(int h, int m,int h1, int m1) {

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		wv_hours1 = (WheelView) view.findViewById(R.id.hour1);
		wv_mins1 = (WheelView) view.findViewById(R.id.min1);

		wv_hours.setAdapter(new ArrayRollerAdapter<String>(list));
		wv_hours.setCyclic(true);// 可循环滚动
		wv_hours.setLabel("点");// 添加文字
		wv_hours.setCurrentItem(h);

		wv_mins.setAdapter(new ArrayRollerAdapter<String>(min));
		wv_mins.setCyclic(false);// 不可循环滚动
		wv_mins.setLabel("分");// 添加文字
		wv_mins.setCurrentItem(m);

		wv_hours1.setAdapter(new ArrayRollerAdapter<String>(list));
		wv_hours1.setCyclic(true);// 可循环滚动
		wv_hours1.setLabel("点");// 添加文字
		wv_hours1.setCurrentItem(h1);

		wv_mins1.setAdapter(new ArrayRollerAdapter<String>(min));
		wv_mins1.setCyclic(false);// 不可循环滚动
		wv_mins1.setLabel("分");// 添加文字
		wv_mins1.setCurrentItem(m1);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (iTime == 0)
			textSize = (screenheight / 100) * 3;
		else if (iTime == 1)
			textSize = (screenheight / 100) * 4;
		else if (iTime == 2)
			textSize = (screenheight / 100) * 4;

		wv_hours.TEXT_SIZE = 35;
		wv_mins.TEXT_SIZE = 35;
		wv_hours1.TEXT_SIZE = 35;
		wv_mins1.TEXT_SIZE = 35;
	}

	public void setStartHour( int hour){
		this.startHour = hour;
	}

	public int getStartHour(){
		return startHour;
	}

	public String getStartTime() {
		StringBuffer sb = new StringBuffer();
		get_hours = list.get(wv_hours.getCurrentItem()).get("text").toString();
		get_mins = list.get(wv_mins.getCurrentItem()).get("text").toString();

		setStartHour(wv_hours.getCurrentItem());

		sb.append(get_hours).append(":")
		.append(get_mins);

		return sb.toString();
	}

	public String getEndTime(){
		StringBuffer sb = new StringBuffer();
		get_hours1 = list.get(wv_hours1.getCurrentItem()).get("text").toString();
		get_mins1 = list.get(wv_mins1.getCurrentItem()).get("text").toString();
		setEndHour(wv_hours1.getCurrentItem());
		sb.append(get_hours1).append(":").append(get_mins1);
		return sb.toString();
	}

}
