package com.nkdark.bot.plugins;

import com.nkdark.bot.module.dice.Personality;
import com.nkdark.bot.module.dice.Random;
import com.nkdark.bot.pojo.SettingInfo;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/1 0:45
 * @Description:
 */

@Component
public class Dice extends CQPlugin {
    private static final Personality P = Personality.getInstance();

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        if (!SettingInfo.isDice()){
            return MESSAGE_IGNORE;
        }
        String dicePrefix = ".";
        String msg = event.getMessage();
        if (msg.startsWith(dicePrefix)){
            // 获取请求的信息
            long userId = event.getUserId();
            long groupId = event.getGroupId();
            //获取指令内容
            String context = msg.substring(1);
            String space = " ";
            if (context.startsWith(space)){
                context = context.substring(1);
            }
            // 今日人品
            String personality = "jrrp";
            if (personality.equals(context)){
                cq.sendGroupMsg(groupId, P.getPersonality(userId,event), false);
                return MESSAGE_BLOCK;
            }
            String randomRegex = "^r[0-9]{0,3}d.*";
            if (context.matches(randomRegex)){
                Random.getRandom(cq, event);
                return MESSAGE_BLOCK;
            }
            return MESSAGE_BLOCK;
        }
        return super.onGroupMessage(cq, event);
    }
}
