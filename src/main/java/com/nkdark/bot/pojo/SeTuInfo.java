package com.nkdark.bot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: NKDark
 * @Date: Create in 2020/8/24
 * @Time: 10:39
 * @Description: 用来接收API返回的Json对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeTuInfo {
    private List<Result> result;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Result {
        private int bookmarkCount;
        private int commentCount;
        private String error;
        private int id;
        private int illustType;
        private int isR18;
        private int likeCount;
        private String message;
        private String original;
        private int pageCount;
        private int pid;
        private String purl;
        private String reverse_url;
        private String tag;
        private String title;
        private int uid;
        private String userName;
        private int viewCount;
    }

    @Override
    public String toString() {
        return result.get(0).toString();
    }

}
