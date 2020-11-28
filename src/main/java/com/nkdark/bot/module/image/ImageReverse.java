package com.nkdark.bot.module.image;

import com.nkdark.bot.pojo.SettingInfo;
import com.nkdark.util.FileUtil;
import net.lz1998.cq.retdata.ApiData;
import net.lz1998.cq.retdata.FileData;
import net.lz1998.cq.robot.CoolQ;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 2:02
 * @Description:
 */

public class ImageReverse {

    public static final String IMAGE_TYPE_GIF = ".gif";
    public static final String IMAGE_TYPE_JPG = ".jpg";
    public static final String IMAGE_TYPE_JPEG = ".jpeg";
    public static final String IMAGE_TYPE_BMP = ".bmp";
    public static final String IMAGE_TYPE_PNG = ".png";
    public static final String SOLVED_REVERSE = "_reversed";
    public static final String SOLVED_ROTATE = "_rotated";


    public static String doReverse(CoolQ cq, String msg) {

        String picName = getPicName(cq, msg);
        if (picName.endsWith(IMAGE_TYPE_GIF)) {
            return null;
        }

        double half = 0.5;
        if (Math.random() < half) {
            return reversePic(cq, msg);
        } else {
            return rotateImage(cq, msg);
        }

    }

    private static String rotateImage(CoolQ cq, String msg) {
        try {
            int degree = 0;
//            if (Math.random() < 0.333) {
//                degree = 90;
//            }else if (Math.random() < 0.666){
//                degree = 180;
//            }else {
//                degree = 270;
//            }
            File file = null;
            BufferedImage bufferedimage = null;
            String picName = getPicName(cq, msg);
            String type = FileUtil.getFileType(picName);
            String solvedPath = (SettingInfo.getPath() + "/data/images/handledImages/" + picName.replace(".image.." + type, "") + SOLVED_ROTATE + "." + type).replace("/data/cache", "");
            if (new File(solvedPath).exists()) {
                return solvedPath;
            }
            String pathname = SettingInfo.getPath() + "/" + picName;
            file = new File(pathname);
            bufferedimage = ImageIO.read(file);
            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();
            int imgType = bufferedimage.getColorModel().getTransparency();
            BufferedImage img;
            Graphics2D graphics2d;
            (graphics2d = (img = new BufferedImage(w, h, imgType))
                    .createGraphics()).setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
            graphics2d.drawImage(bufferedimage, 0, 0, null);
            graphics2d.dispose();
            if (picName.endsWith(type)) {
                file = new File(solvedPath);
                ImageIO.write(img, type, file);
            }
            return solvedPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String reversePic(CoolQ cq, String msg) {
        File file = null;
        BufferedImage image = null;
        String picName = getPicName(cq, msg);
        String type = FileUtil.getFileType(picName);
        String solvedPath = (SettingInfo.getPath() + "/data/images/handledImages/" + picName.replace(".image.." + type, "") + SOLVED_REVERSE + "." + type).replace("/data/cache", "");
        if (new File(solvedPath).exists()) {
            return solvedPath;
        }
        try {
            String pathname = SettingInfo.getPath() + "/" + picName;
            file = new File(pathname);
            image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            for (int j = 0; j < height; j++) {
                int l = 0, r = width - 1;
                while (l < r) {
                    int pl = image.getRGB(l, j);
                    int pr = image.getRGB(r, j);

                    image.setRGB(l, j, pr);
                    image.setRGB(r, j, pl);

                    l++;
                    r--;
                }
            }
            if (picName.endsWith(type)) {
                file = new File(solvedPath);
                ImageIO.write(image, type, file);
            }
            return solvedPath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 解析出图片名称
     *
     * @param cq  cq
     * @param msg 原消息
     * @return 图片名称
     */
    private static String getPicName(CoolQ cq, String msg) {
        String imgSign = "[CQ:image,file=";
        if (msg.indexOf(imgSign)!=msg.lastIndexOf(imgSign)){
            return null;
        }
        // 切出文件名
        String file = msg.substring(msg.indexOf(imgSign) + 15, msg.indexOf(".image") + 6);
        // 获取图片
        ApiData<FileData> image = cq.getImage(file);
        // 获取相对路径
        String toEscape = "" + image;
        // 获取cache目录下的文件名
        return toEscape.substring(toEscape.indexOf("file=") + 5, toEscape.length() - 2);
    }


    @Deprecated
    private static String getPicPath(CoolQ cq, String msg) {
        String path = null;
        String result = getPicName(cq, msg);
        // 拼出绝对路径
        String suffix = "/";
        if (SettingInfo.getPath().endsWith(suffix)) {
            path = SettingInfo.getPath() + result;
        } else {
            path = SettingInfo.getPath() + "/" + result;
        }
        // 返回绝对路径
        return path;
    }
}
