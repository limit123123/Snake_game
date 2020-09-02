package com.stu;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //创建一个窗体：窗体类的具体的对象：
        JFrame jf = new JFrame();
        //给窗体设置一个标题：
        jf.setTitle("snake!");
        //求出屏幕的宽度：
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        //求出屏幕的高度：
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //窗体在什么位置， 设置宽高
        /*
        第一个参数：x轴坐标
        第二个参数：y轴坐标
        第三个参数：宽
        第四个参数：高
         */
        jf.setBounds((width - 800)/2,(height - 800)/2,800,800);
        //让窗体的大小不可以改变：
        jf.setResizable(false);
        //点击窗体的×的时候，程序跟着停止运行：
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //创建面板对象放入窗体：
        GamePanel g = new GamePanel();
        jf.add(g);

        //默认情况下，生成的窗体是不显现的，现在要手动将这个窗体显现出来：
        jf.setVisible(true);



    }
}
