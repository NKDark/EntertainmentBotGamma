package com.nkdark.bot.module.mlrepeat;

import com.nkdark.bot.pojo.SettingInfo;
import com.nkdark.util.StrTools;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MessageCollectPlugin extends CQPlugin {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long groupId = event.getGroupId();
        long userId = event.getSender().getUserId();
        String msg = event.getMessage();
        String regex = ".*\\[CQ:image,file=.*url=.*\\].*";
        if (msg.contains("[CQ:") && !msg.matches(regex)) {
            return MESSAGE_IGNORE;
        }
        if (msg.matches(regex)) {
            int count = StrTools.getSubCount(msg, "[CQ:image,file=");
            if (count == 1) {
                try {
                    msg = msg.substring(0, msg.indexOf("-") + 1) + msg.substring(msg.lastIndexOf("-"));
                    msg = msg.substring(0, msg.indexOf("new/") + 4) + msg.substring(msg.indexOf("/", msg.indexOf("new/") + 4));
                    msg = msg.substring(0, msg.indexOf("new/") + 5) + msg.substring(msg.indexOf("--"));
                    msg = msg.substring(0, msg.lastIndexOf("?")) + msg.substring(msg.indexOf("]"));
                }catch (Exception e){
                    cq.sendPrivateMsg(SettingInfo.getAdminId(),msg+"\n"+e.toString(),false);
                }
            } else {
                return MESSAGE_IGNORE;
            }
        }
        if (msg.startsWith(".")) {
            return MESSAGE_IGNORE;
        }
        if (msg.contains("带可") || msg.contains("nk") || msg.contains("带客") || msg.contains("图")) {
            return MESSAGE_IGNORE;
        }
        if (StrTools.isEmpty(msg)) {
            return MESSAGE_IGNORE;
        }
        String date = sdf.format(new Date(System.currentTimeMillis()));
        String a = null;
        try {
            a = jdbcTemplate.queryForObject("SELECT `COUNT` AS A FROM `MESSAGE` WHERE `GROUP_ID` = \"" + groupId + "\" AND `SENDER_ID` = \"" + userId + "\" AND `CONTEXT` = \"" + msg + "\"", String.class);
        } catch (EmptyResultDataAccessException e) {

        }
        if (!StrTools.isEmpty(a)) {
            int i = Integer.parseInt(a);
            i++;
            jdbcTemplate.execute("UPDATE `MESSAGE` SET `COUNT` = " + i + " WHERE `SENDER_ID` = \""+userId+"\" AND `CONTEXT` = \"" + msg + "\"");
        } else {
            jdbcTemplate.execute("INSERT INTO `MESSAGE`(`GROUP_ID`,`SENDER_ID`,`CONTEXT`,`SEND_TIME`) VALUES(" + groupId + "," + userId + ",\"" + msg + "\",\"" + date + "\")");
        }
        return MESSAGE_IGNORE;
    }
}
