package com.kkandroidstudy.bean;

/**
 * Created by shiyan on 2016/11/23.
 */

public class PieData {
    //用户关心的数据
    //名字
    private String name;
    //数值
    private float value;
    //百分比
    private float percentage;

    //非用户关心数据
    //颜色
    private int color;
    //角度
    private float angel;

    public PieData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngel() {
        return angel;
    }

    public void setAngel(float angel) {
        this.angel = angel;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", color=" + color +
                ", angel=" + angel +
                '}';
    }
}
