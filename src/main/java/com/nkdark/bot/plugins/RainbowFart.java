package com.nkdark.bot.plugins;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/8/21 1:46
 * @Description:
 */
@Component
public class RainbowFart extends CQPlugin {

    String[] keywords = {"彩虹屁", "夸我", "网抑云时间", "动画", "漫画", "游戏", // 0 - 5
            "文学", "原创", "一言", "其他", "影视", // 6 - 10
            "诗词", "网抑云", "哲学", "抖机灵", "渣男语录", // 11 - 15
            "毒鸡汤", "嘴臭", "舔狗日记" // 16 - 18
    };

    // 彩虹屁
    String fart = "https://chp.shadiao.app/api.php";
    // 网抑云
    String wyy = "https://api.lo-li.icu/wyy/";
    // 一言 - 动画
    String dh = "https://international.v1.hitokoto.cn/?c=a&encode=text";
    // 一言 - 漫画
    String mh = "https://international.v1.hitokoto.cn/?c=b&encode=text";
    // 一言 - 游戏
    String yx = "https://international.v1.hitokoto.cn/?c=c&encode=text";
    // 一言 - 文学
    String wx = "https://international.v1.hitokoto.cn/?c=d&encode=text";
    // 一言 - 原创
    String yc = "https://international.v1.hitokoto.cn/?c=e&encode=text";
    // 一言 - 来自网络
    String wl = "https://international.v1.hitokoto.cn/?c=f&encode=text";
    // 一言 - 其他
    String other = "https://international.v1.hitokoto.cn/?c=g&encode=text";
    // 一言 - 影视
    String movie = "https://international.v1.hitokoto.cn/?c=h&encode=text";
    // 一言 - 诗词
    String poem = "https://international.v1.hitokoto.cn/?c=i&encode=text";
    // 一言 - 网抑云
    String wyy1 = "https://international.v1.hitokoto.cn/?c=j&encode=text";
    // 一言 - 哲学
    String philosophy = "https://international.v1.hitokoto.cn/?c=k&encode=text";
    // 一言 - 抖机灵
    String Trembling = "https://international.v1.hitokoto.cn/?c=l&encode=text";
    // 渣男语录
    String dregs = "https://api.lovelive.tools/api/SweetNothings";
    // 毒鸡汤
    String poisonSoup = "https://du.shadiao.app/api.php";
    // 嘴臭
    String fetor = "https://nmsl.shadiao.app/api.php?level=min&lang=zh_cn";
    // 舔狗日记
    String licker = "https://api.oick.cn/dog/api.php";


    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long userId = event.getUserId();
        String msg = event.getMessage();
        // 彩虹屁
        if (keywords[0].equals(msg) || keywords[1].equals(msg)) {
            cq.sendPrivateMsg(userId, getString(fart), false);
            return MESSAGE_BLOCK;
        }
        // 网抑云
        if (keywords[2].equals(msg) || keywords[12].equals(msg)) {
            String res = null;
            if (Math.random() > 0.5) {
                res = wyy;
            } else {
                res = wyy1;
            }
            cq.sendPrivateMsg(userId, getString(res), false);
            return MESSAGE_BLOCK;
        }

        // 动画
//        if (keywords[3].equals(msg)) {
//            cq.sendPrivateMsg(userId, getString(dh),false);
//            return MESSAGE_BLOCK;
//        }

        // 漫画
//        if (keywords[4].equals(msg)){
//            cq.sendPrivateMsg(userId, getString(mh),false);
//            return MESSAGE_BLOCK;
//        }

        // 鸡汤 - 来自网络
        if (keywords[8].equals(msg)) {
            cq.sendPrivateMsg(userId, getString(other), false);
            return MESSAGE_BLOCK;
        }

        // 诗词
        if (keywords[11].equals(msg)) {
            cq.sendPrivateMsg(userId, getString(poem), false);
            return MESSAGE_BLOCK;
        }

        // 抖机灵
//        if (keywords[14].equals(msg)) {
//            cq.sendPrivateMsg(userId, getString(Trembling), false);
//            return MESSAGE_BLOCK;
//        }

        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        long userId = event.getUserId();
        long groupId = event.getGroupId();
        String msg = event.getMessage();
        // 彩虹屁
        if (keywords[0].equals(msg) || keywords[1].equals(msg)) {
            cq.sendGroupMsg(groupId, CQCode.at(userId) + getString(fart), false);
            return MESSAGE_BLOCK;
        }
        // 网抑云
        if (keywords[2].equals(msg) || keywords[12].equals(msg)) {
            String res = null;
            if (Math.random() > 0.5) {
                res = wyy;
            } else {
                res = wyy1;
            }
            cq.sendGroupMsg(groupId, getString(res), false);
            return MESSAGE_BLOCK;
        }
        // 鸡汤 - 来自网络
        if (keywords[8].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(other), false);
            return MESSAGE_BLOCK;
        }
        // 诗词
        if (keywords[11].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(poem), false);
            return MESSAGE_BLOCK;
        }

        // 抖机灵
//        if (keywords[14].equals(msg)) {
//            cq.sendGroupMsg(groupId, getString(Trembling), false);
//            return MESSAGE_BLOCK;
//        }

        // 渣男语录
        if (keywords[15].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(dregs), false);
            return MESSAGE_BLOCK;
        }
        // 毒鸡汤
        if (keywords[16].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(poisonSoup), false);
            return MESSAGE_BLOCK;
        }
        // 嘴臭
        if (keywords[17].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(fetor), false);
            return MESSAGE_BLOCK;
        }
        // 舔狗日记
        if (keywords[18].equals(msg)) {
            cq.sendGroupMsg(groupId, getString(licker), false);
            return MESSAGE_BLOCK;
        }

        return MESSAGE_IGNORE;
    }

    public String getString(String requestUrl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();// 关闭远程连接
            }
        }
        return null;
    }


}
