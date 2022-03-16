package org.butu.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @program: BTForum
 * @description: 验证码
 * @packagename: org.butu.utils
 * @author: BUTUbird
 * @date: 2022-03-16 17:01
 **/
public class VerifyCodeUtils {

    /**
     * 宽高可作为参数传入
     */
    private static final int WIDTH = 300;
    private static final int HEIGHT = 75;

    /**
     * 传入BufferedImage对象，并将生成好的验证码保存到BufferedImage中
     * @param bufferedImage
     * @return
     */
    public static String drawRandomText(BufferedImage bufferedImage) {
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        // 设置画笔颜色-验证码背景色
        graphics.setColor(Color.WHITE);
        // 填充背景
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setFont(new Font("宋体,楷体,微软雅黑", Font.BOLD, 40));

        // 数字和字母的组合
        String baseNumLetter = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

        StringBuilder builder = new StringBuilder();
        // 旋转原点的 x 坐标
        int x = 10;
        String ch;
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            graphics.setColor(getRandomColor());

            //设置字体旋转角度,角度小于30度
            int degree = random.nextInt() % 30;
            int dot = random.nextInt(baseNumLetter.length());

            ch = baseNumLetter.charAt(dot) + "";
            builder.append(ch);

            //正向旋转
            graphics.rotate(degree * Math.PI / 180, x, 45);
            graphics.drawString(ch, x, 45);

            //反向旋转
            graphics.rotate(-degree * Math.PI / 180, x, 45);
            x += 48;
        }

        //画干扰线
        for (int i = 0; i < 6; i++) {
            // 设置随机颜色
            graphics.setColor(getRandomColor());

            // 随机画线
            graphics.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                    random.nextInt(WIDTH), random.nextInt(HEIGHT));

        }

        //添加噪点
        for (int i = 0; i < 30; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);

            graphics.setColor(getRandomColor());
            graphics.fillRect(x1, y1, 2, 2);
        }
        return builder.toString();
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));

    }
}
