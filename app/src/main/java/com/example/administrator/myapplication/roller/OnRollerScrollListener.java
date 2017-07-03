package com.example.administrator.myapplication.roller;


/**
 * WheelView 滚动监听器
 */
public interface OnRollerScrollListener {
    /**
     * 在 WheelView 滚动开始的时候回调该接口
     * 
     * @param wheel
     *            开始滚动的 WheelView 对象
     */
    void onScrollingStarted(RollerView wheel);

    /**
     * 在 WheelView 滚动结束的时候回调该接口
     * 
     * @param wheel
     *            结束滚动的 WheelView 对象
     */
    void onScrollingFinished(RollerView wheel);
}
