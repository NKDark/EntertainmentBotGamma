package com.nkdark.bot.pojo;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 3:06
 * @Description:
 */

public class SettingInfo {
    private static long AdminId;
    private static boolean Dice;
    private static boolean Repeat;
    private static String Path;
    private static double Threshold;

    public static long getAdminId() {
        return AdminId;
    }

    public static void setAdminId(long adminId) {
        AdminId = adminId;
    }

    public static boolean isDice() {
        return Dice;
    }

    public static void setDice(boolean dice) {
        Dice = dice;
    }

    public static boolean isRepeat() {
        return Repeat;
    }

    public static void setRepeat(boolean repeat) {
        Repeat = repeat;
    }

    public static String getPath() {
        return Path;
    }

    public static void setPath(String path) {
        Path = path;
    }

    public static double getThreshold() {
        return Threshold;
    }

    public static void setThreshold(double threshold) {
        Threshold = threshold;
    }
}
