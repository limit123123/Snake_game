package com.stu;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Images {
    //加载需要的图片到窗体中,static访问会更快
    public static URL headURL= Images.class.getResource("/images/header.png");
    public static  ImageIcon headerImg=new ImageIcon(headURL);

    public static URL downURL= Images.class.getResource("/images/down.png");
    public static ImageIcon downImg=new ImageIcon(downURL);

    public static URL foodURL= Images.class.getResource("/images/food.png");
    public static ImageIcon foodImg=new ImageIcon(foodURL);

    public static URL bodyURL= Images.class.getResource("/images/body.png");
    public static ImageIcon bodyImg=new ImageIcon(bodyURL);

    public static URL leftURL= Images.class.getResource("/images/left.png");
    public static ImageIcon leftImg=new ImageIcon(leftURL);

    public static URL rightURL= Images.class.getResource("/images/right.png");
    public static ImageIcon rightImg=new ImageIcon(rightURL);

    public static URL upURL= Images.class.getResource("/images/up.png");
    public static ImageIcon upImg=new ImageIcon(upURL);


}
