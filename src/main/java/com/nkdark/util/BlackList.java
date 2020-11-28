package com.nkdark.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BlackList {
    private static List<String> qqNumberBlacklist = new ArrayList<>();
    private static List<String> groupNumberBlacklist = new ArrayList<>();
    private static List<String> keyWordBlacklist = new ArrayList<>();

    private static final String PATH_A = "qqNumberBlacklist.txt";
    private static final String PATH_B = "groupNumberBlacklist.txt";
    private static final String PATH_C = "keyWordBlacklist.txt";

    // 初始化黑名单文件
    static {
        File fileA = new File(PATH_A);
        File fileB = new File(PATH_B);
        File fileC = new File(PATH_C);
        if (!fileA.exists()) {
            try {
                fileA.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileA));
                String line = null;
                while ((line = br.readLine()) != null) {
                    qqNumberBlacklist.add(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!fileB.exists()) {
            try {
                fileB.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileA));
                String line = null;
                while ((line = br.readLine()) != null) {
                    groupNumberBlacklist.add(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!fileC.exists()) {
            try {
                fileB.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileA));
                String line = null;
                while ((line = br.readLine()) != null) {
                    keyWordBlacklist.add(line);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int addQQBlockList(String qqNumber) {
        FileWriter fw = null;
        if (qqNumberBlacklist.contains(qqNumber)) {
            return -1;
        }
        qqNumberBlacklist.add(qqNumber);
        try {
            fw = new FileWriter(PATH_A, true);
            fw.write(qqNumber + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static boolean isQQBlocked(String qqNumber) {
        return qqNumberBlacklist.contains(qqNumber);
    }

    public static String checkQQBlackList() {
        return qqNumberBlacklist.toString();
    }
}
