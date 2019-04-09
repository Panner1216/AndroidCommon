package com.panner.common_base.utils;

/**
 * @author Panner
 * @version 2019-04-07-22:46
 */
public class NumberUtils {
    private static String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static String pos_units[] = {"", "十", "百", "千"};

    private static String weight_units[] = {"", "万", "亿"};

    /**
     * 数字转汉字【新】
     *
     * @param num
     * @return
     */
    public static String numberToChinese(int num) {
        if (num == 0) {
            return "零";
        }
        if (num == 7) {
            return "日";
        }
        if (num == 8) {
            return "全";
        }
        int weigth = 0;//节权位
        String chinese = "";
        String chinese_section = "";
        boolean setZero = false;//下一小节是否需要零，第一次没有上一小节所以为false
        while (num > 0) {
            int section = num % 10000;//得到最后面的小节
            if (setZero) {//判断上一小节的千位是否为零，是就设置零
                chinese = nums[0] + chinese;
            }
            chinese_section = sectionTrans(section);
            if (section != 0) {//判断是都加节权位
                chinese_section = chinese_section + weight_units[weigth];
            }
            chinese = chinese_section + chinese;
            chinese_section = "";

            setZero = (section < 1000) && (section > 0);
            num = num / 10000;
            weigth++;
        }
        if ((chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十")) {
            chinese = chinese.substring(1, chinese.length());
        }
        if (chinese.indexOf("一十") == 0) {
            chinese = chinese.replaceFirst("一十", "十");
        }

        return chinese;
    }

    /**
     * 将每段数字转汉子
     *
     * @param section
     * @return
     */
    public static String sectionTrans(int section) {
        StringBuilder section_chinese = new StringBuilder();
        int pos = 0;//小节内部权位的计数器
        boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
        while (section > 0) {
            int v = section % 10;//得到最后一个数
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    section_chinese.insert(0, nums[0]);
                }
            } else {
                zero = false;//有非零数字就把置零打开
                section_chinese.insert(0, pos_units[pos]);
                section_chinese.insert(0, nums[v]);
            }
            pos++;
            section = section / 10;
        }

        return section_chinese.toString();
    }

    /**
     * 十进制字符串转换成radix进制字符串
     *
     * @param leftZero 左边加几个零的格式输出
     * @param num
     * @return
     */
    public static String sysConvertAndLeftZero(String num, int leftZero) {
        String string = Integer.toHexString(Integer.valueOf(num));
        return BinHexOct.addZeroForNumLeft(string, leftZero);
    }

    /**
     * radix进制字符串转换为十进制，在右边加零返回
     *
     * @param num
     * @param radix
     * @param rightZero
     * @return
     */
    public static String sysConverAndRightZero(String num, int radix, int rightZero) {
        Integer integer = Integer.valueOf(num, radix);
        return BinHexOct.addZeroForNumRight(String.valueOf(integer), rightZero);
    }
}
