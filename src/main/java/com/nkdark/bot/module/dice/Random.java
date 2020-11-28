package com.nkdark.bot.module.dice;

import com.nkdark.util.DiceUtil;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 4:39
 * @Description:
 */

public class Random {
    public static void getRandom(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long gid = event.getGroupId();

        final int indexR = msg.indexOf("r");
        final int indexD = msg.indexOf("d");
        int count = 0;
        int surface = 0;

        String dueTo = null;

        try {
            // 匹配无空格
            if (!msg.contains(" ")) {
                if (indexD - indexR == 1) {
                    count = 1;
                } else {
                    count = Integer.parseInt(msg.substring(indexR + 1, indexD));
                }
                if (msg.length() - indexD == 1) {
                    surface = 100;

                } else {
                    surface = Integer.parseInt(msg.substring(indexD + 1));
                }
                if (count <= 100 && surface <= 1000) {
                    long res = DiceUtil.roll(count, surface);
                    cq.sendGroupMsg(gid, "result: " + res, false);
                } else {
                    cq.sendGroupMsg(gid, "对不起，做不到", false);
                }
            } else {
                final int indexSpace = msg.indexOf(" ");
                dueTo = msg.substring(indexSpace + 1);
                // 判断count
                if (indexD - indexR == 1) {
                    count = 1;
                } else {
                    count = Integer.parseInt(msg.substring(indexR + 1, indexD));
                }
                // 判断surface
                if (indexSpace - indexD == 1) {
                    surface = 100;
                } else {
                    surface = Integer.parseInt(msg.substring(indexD + 1, indexSpace));
                }
                if (count <= 100 && surface <= 1000) {
                    long res = DiceUtil.roll(count, surface);
                    cq.sendGroupMsg(gid, "由于 " + dueTo + " : " + res, false);
                } else {
                    cq.sendGroupMsg(gid, "对不起，做不到", false);
                }
            }

        } catch (NumberFormatException ignore) {
            cq.sendGroupMsg(gid, CQCode.at(event.getUserId()) + "格式错误", false);
        }
    }
}
