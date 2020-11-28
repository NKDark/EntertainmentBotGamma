package com.nkdark.bot.plugins;

import com.nkdark.bot.module.image.ImageReverse;
import com.nkdark.bot.pojo.SettingInfo;
import com.nkdark.util.StrTools;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 1:55
 * @Description:
 */

@Component
public class Repeat extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        String isImage = "[CQ:image,file=";
        if (msg.contains(isImage)) {
            // 图片反转功能
            if (SettingInfo.getThreshold() > Math.random() && StrTools.isImage(msg)) {
                String s = ImageReverse.doReverse(cq, msg);
                if (s == null) {
                    return MESSAGE_BLOCK;
                }
                cq.sendGroupMsg(groupId, CQCode.image("file:///" + s), false);
            }
            return MESSAGE_BLOCK;
        }

        return MESSAGE_IGNORE;
    }
}
