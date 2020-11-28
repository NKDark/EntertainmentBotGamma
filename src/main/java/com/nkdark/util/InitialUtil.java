package com.nkdark.util;

import com.nkdark.bot.pojo.SettingInfo;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 2:39
 * @Description:
 */

@Component
public class InitialUtil {
    private static Properties prop = null;

    static {
        init();
        if (SettingInfo.isRepeat()){
            mkdir();
        }
    }

    private static void mkdir() {
        File image = new File(SettingInfo.getPath() + "/data/images/handledImages");
        if (!image.exists()) {
            image.mkdirs();
        }
        File mods = new File("./mods");
        if (!mods.exists()) {
            mods.mkdirs();
        }
    }

    /**
     *  初始化配置文件
     */
    private static void init(){
        try {
            File config = new File("config.properties");
            // 如果判断配置文件不存在的话就新建
            if (!config.exists()) {
                config.createNewFile();
                OutputStream os = new FileOutputStream(new File("config.properties"));
                os.write(
                        ("#go-cqhttp的绝对路径\nPath=\n" +
                        "#管理员账号\nAdminId=-1\n" +
                        "#骰子功能总开关\nDice=true\n" +
                        "#复读功能总开关\nRepeat=true\n" +
                        "#复读概率\nThreshold=0.05\n"
                        ).getBytes());
                os.flush();
                os.close();
                System.out.println("初始化成功，请修改配置文件\n请在修改配置文件之后重新运行");
                System.exit(0);
            }
            // 开启输入流读取配置文件
            InputStream is = new FileInputStream(new File("config.properties"));
            prop = new Properties();
            prop.load(is);
            SettingInfo.setAdminId(Integer.parseInt(prop.getProperty("AdminId")));
            SettingInfo.setDice(Boolean.parseBoolean(prop.getProperty("Dice")));
            SettingInfo.setRepeat(Boolean.parseBoolean(prop.getProperty("Repeat")));
            SettingInfo.setThreshold(Double.parseDouble(prop.getProperty("Threshold")));
            SettingInfo.setPath(prop.getProperty("Path"));
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
