package com.nkdark.util;

import com.nkdark.bot.pojo.SettingInfo;

import java.io.File;

/**
 * Create by Intellij IDEA
 *
 * @Author: NKDark
 * @Date: create in 2020/9/12 3:03
 * @Description:
 */

public class FileUtil {
    /**
     * 检查是否有已处理过的文件
     * @param fileName 文件名
     * @return 是否
     */
    public static boolean isImageExist(String fileName){
        String filePath = SettingInfo.getPath();

        File file = new File("");

        return false;
    }

    public static String getFileType(String name) {
        File file = new File(name);
        String fileName = file.getName();
        int idx =  fileName.lastIndexOf(".");
        if(idx == -1){
            throw new RuntimeException("Invalid file name");
        }

        return fileName.substring(idx+1);
    }
}
