package com.nkdark.bot.plugins;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MLRepeat extends CQPlugin {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        long groupId = event.getGroupId();

        // 有10%概率进入科学复读
        if (Math.random() > 0.985) {
            try {
                Thread.sleep(new Random(System.currentTimeMillis()).nextInt(60000));
                String msg = jdbcTemplate.queryForObject("SELECT `CONTEXT` FROM `MESSAGE` WHERE `COUNT` > 3 AND `GROUP_ID` = " + groupId + " ORDER BY RAND() LIMIT 1", String.class);
                cq.sendGroupMsg(groupId, msg, false);
            } catch (Exception e) {

            }
        }

        return super.onGroupMessage(cq, event);
    }
}
