package com.nkdark.bot.plugins;

import com.nkdark.util.BlackList;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

@Component
public class BlockList extends CQPlugin {
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        long userId = event.getUserId();
        if (BlackList.isQQBlocked(userId+"")){
            return MESSAGE_BLOCK;
        }
        return super.onPrivateMessage(cq, event);
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        long userId = event.getUserId();
        if (BlackList.isQQBlocked(userId+"")){
            return MESSAGE_BLOCK;
        }
        return super.onGroupMessage(cq, event);
    }
}
