package com.nkdark.bot.schedule;

import com.nkdark.util.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CleanMessageTask {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 0 1 * * ?", zone = "Asia.Shanghai")
    public void cleanMessage() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(DateTools.getPastDay(new Date(System.currentTimeMillis()), 3));
        jdbcTemplate.execute("DELETE FROM `MESSAGE` WHERE `COUNT` <= 2 AND `SEND_TIME` <= '" + date + "'");
    }

}
