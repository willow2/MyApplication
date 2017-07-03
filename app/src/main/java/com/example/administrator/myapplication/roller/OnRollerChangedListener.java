package com.example.administrator.myapplication.roller;


/**
 * 条目改变监听器
 */
public interface OnRollerChangedListener {
    /**
     * 当前条目改变时回调该方法
     * 
     * @param wheel
     *            条目改变的 WheelView 对象
     * @param oldValue
     *            WheelView 旧的条目值
     * @param newValue
     *            WheelView 新的条目值
     */
    void onChanged(RollerView wheel, int oldValue, int newValue);
}

