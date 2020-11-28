package com.nkdark.bot.module.dice;

import com.nkdark.util.DateTools;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import org.springframework.context.annotation.ComponentScan;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/10 23:35
 * @Description:
 */
@ComponentScan
public class Personality extends CQPlugin {
    private static volatile Personality personality = null;

    private Personality() {}

    public static Personality getInstance() {
        if (personality == null) {
            synchronized (Personality.class) {
                if (personality == null) {
                    personality = new Personality();
                }
            }
        }
        return personality;
    }

    public String getPersonality(long userId, CQGroupMessageEvent event) {
        int i = DateTools.randomPersonality(userId);
        String resp = null;
        if (event.getSender().getCard().length() < 1) {
            resp = event.getSender().getNickname();
        } else {
            resp = event.getSender().getCard();
        }
        return resp + "今天的人品值是：" + i;
    }

}
