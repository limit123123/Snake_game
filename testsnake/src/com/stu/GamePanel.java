package com.stu;

import com.sun.javafx.iio.ImageStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    //定义小蛇的长度：
    int length;
    //定义一个数组，专门用来存储小蛇的x轴坐标;
    int[] snakeX = new int[600];
    //定义一个数组，专门用来存储小蛇的y轴坐标;
    int[] snakeY = new int[600];
    //加入一个小蛇的行走方向：
    String direction ;
    //加入一个布尔类型的变量，来指定游戏是否开始：
    boolean isStart = false;//默认游戏没有开始
    //引入一个定时器：
    Timer timer;
    //定义食物的xy轴坐标：
    int foodX;
    int foodY;
    int score;
    boolean isdie;
    //对定义的属性进行初始化操作：
    public void init(){
        //初始化蛇的长度为3
        length = 3;
        //初始化  蛇头 坐标：
        snakeX[0] = 150;
        snakeY[0] = 275;
        //初始化 第一节身子 坐标：
        snakeX[1] = 125;
        snakeY[1] = 275;
        //初始化  第二节身子 坐标：
        snakeX[2] = 100;
        snakeY[2] = 275;
        //定义小蛇初始化方向：
        direction = "R";
        //初始化食物的坐标：
        foodX = 275;
        foodY = 125;
        score=0;
        isdie=false;
    }
    public GamePanel(){
        init();
        //将 系统焦点 放在面板上：
        this.setFocusable(true);
        //加入一个监听器：
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//监听按下去的动作：
                super.keyPressed(e);
                //获取你按的按键：
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if(keyCode == KeyEvent.VK_SPACE){//按住空格了
                    if(isdie){
                        init();
                    }
                    //将isStart设置为相反的值？？？
                    isStart = !isStart;

                    //页面重新绘制：
                    repaint();//底层调用的还是paintComponent
                }
                if(keyCode == KeyEvent.VK_UP){
                    direction = "U" ;
                }
                if(keyCode == KeyEvent.VK_DOWN){
                    direction = "D" ;
                }
                if(keyCode == KeyEvent.VK_LEFT){
                    direction = "L" ;
                }
                if(keyCode == KeyEvent.VK_RIGHT){
                    direction = "R" ;
                }
            }
        });
        //给定时器初始化 ---》  创建定时器具体的对象：100ms
        timer = new Timer(100, new ActionListener() {
            //actionPerformed 就是一个 每100毫秒 程序执行一次的方法：
            @Override
            public void actionPerformed(ActionEvent e) {
                //每100毫秒，蛇的坐标要动一次：
                if(isStart == true&&isdie==false){//如果游戏是开始的，那么小蛇动一次
                    //蛇开始动 --》 坐标动：
                    //先动身子，再动头：
                    //身子动
                    for (int i = length-1; i > 0 ; i--) {
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    //头动：
                    //按照行走 的方向改变蛇头的坐标：
                    switch (direction){
                        case "U" : snakeY[0] = snakeY[0] - 25;break;
                        case "D" : snakeY[0] = snakeY[0] + 25;break;
                        case "L" : snakeX[0] = snakeX[0] - 25;break;
                        case "R" : snakeX[0] = snakeX[0] + 25;break;
                    }
                    //给蛇行走设置边界：防止小蛇飞出去：
                    if(snakeX[0] > 750){
                        snakeX[0] = 25;
                    }
                    if(snakeX[0] <25){
                        snakeX[0] = 750;
                    }
                    if(snakeY[0] < 100){
                        snakeY[0] = 725;
                    }
                    if(snakeY[0] > 725){
                        snakeY[0] = 100;
                    }
                    //当蛇头和食物碰撞的时候，食物的坐标改变：
                    if(snakeX[0] == foodX&&snakeY[0] == foodY){
                        //蛇长度加1：
                        length++;
                        //食物的坐标发生改变：
                        /*
                        [25,725]之间被25整除的数
                        [25,725]/25 = [1,29]*25 = [25,725]
                        [1,29]:
                        Math.random - [0.0,1.0)
                        Math.random*29 - [0.0,29.0)
                        (int)(Math.random*29) - [0,28]
                        (int)(Math.random*29) + 1 - [1,29]
                        ((int)(Math.random*29) + 1)*25 - [25,725]
                         */
                        foodX = ((int)(Math.random()*29) + 1)*25;
                        foodY = ((int)(Math.random()*26) + 4)*25; //[100,725]/25 = [4,29]*25   -- ([1,26]+3)*25
                    score=(length-3)*10;//蛇增加的长度乘以10

                    }
                    for (int i = 1; i < length; i++) {
                        if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                            isdie=true;//蛇的头部与其他部位碰撞
                        }
                    }
                    //在蛇坐标改完以后，页面刷新：
                    repaint();
                }
            }
        });
        //定时器必须要启动：
        timer.start();
    }
    //重写一个方法：paintComponent
    //所有在面板中画图的逻辑，都要写在这个方法里
    //paintComponent底层自动调用，无需我们调用
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //设置面板的背景颜色：
        this.setBackground(new Color(209, 237, 239));
        //插入一个头部图片：
//        Images.headerImg.paintIcon(this,g,10,10);
        //画一个矩形区域：
        //将画笔的颜色改变一下：
        g.setColor(new Color(239, 219, 218));
        g.fillRect(10,70,770,685);
        //在对应的位置，将蛇头画进去：
        switch (direction){
            case "L" : Images.leftImg.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "R" : Images.rightImg.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "U" : Images.upImg.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "D" : Images.downImg.paintIcon(this,g,snakeX[0],snakeY[0]);break;
        }
        //在对应的位置，将第1节身子画进去：
        //Images.bodyImg.paintIcon(this,g,snakeX[1],snakeY[1]);
        //在对应的位置，将第2节身子画进去：
        //Images.bodyImg.paintIcon(this,g,snakeX[2],snakeY[2]);
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //游戏没有开始，面板中要加入一句话加入一个提示：点击空格游戏开始：
        if(isStart == false){//游戏是暂停的：
            //中间写入文字：
            g.setColor(new Color(159, 123, 214));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("use space to start",200,330);
        }
        //画食物：
        Images.foodImg.paintIcon(this,g,foodX,foodY);
        //积分
        g.setColor(new Color(123, 167, 214));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("curent score:"+score,360,40);
        if(isdie){
            g.setColor(new Color(187, 20, 112));
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("Your dear snake is die,please use space to restart!",10,300);

        }

    }
}
