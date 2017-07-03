package com.example.administrator.myapplication.timeview;

import java.util.List;
import java.util.Map;


/**
 * WheelView 的适配器类
 * 
 * @param <T>
 *            元素类型
 */
public class ArrayRollerAdapter<T> implements WheelAdapter {

    /** 适配器的 元素集合(数据源) 默认长度为 -1 */
    public static final int DEFAULT_LENGTH = -1;

    
    /** WheelView 的宽度 */
    private int length;
    /** 适配器的数据源 */
	private List<Map<String, Object>> list;

    /**
     * 构造方法
     * 
     * @param sexlist
     *            适配器数据源 集合 T 类型的数组
     * @param length
     *            适配器数据源 集合 T 数组长度
     */
    public ArrayRollerAdapter(List<Map<String, Object>> list, int length) {
        this.list = list;
        this.length = length;
    }

    /**
     * 构造方法
     * 
     * @param sexlist
     *            适配器数据源集合 T 类型数组
     */
    public ArrayRollerAdapter(List<Map<String, Object>> list) {
        this(list, DEFAULT_LENGTH);
    }

    
    @Override
    public String getItem(int index) {
    	//如果这个索引值合法, 就返回 item 数组对应的元素的字符串形式
        if (index >= 0 && index < list.size()) {
            return list.get(index).get("text").toString();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
    	//返回 item 数组的长度
        return list.size();
    }

    @Override
    public int getMaximumLength() {
    	//返回 item 元素的宽度
        return length;
    }

}
