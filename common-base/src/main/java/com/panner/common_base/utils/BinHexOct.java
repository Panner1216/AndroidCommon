package com.panner.common_base.utils;

import android.text.TextUtils;

/**
 * 进制转换工具类
 *
 * @author Panner
 * @version 2019-04-07-22:49
 */
public class BinHexOct {

    static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * bytes转换为十六进制的字符串
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * 十六进制字符串转换成整数int
     *
     * @param valueStr 十六进制的字符串
     * @return
     */
    public static int hexStrToInt(String valueStr) {
        valueStr = valueStr.toUpperCase();
        if (valueStr.length() % 2 != 0) {
            valueStr = "0" + valueStr;
        }

        int returnValue = 0;

        int length = valueStr.length();

        for (int i = 0; i < length; i++) {

            int value = charToByte(valueStr.charAt(i));

            returnValue += Math.pow(16, length - i - 1) * value;
        }
        return returnValue;
    }

    /**
     * 将字节数组转换为string
     *
     * @param bytes 需要转换的字节数组
     * @return
     */
    public static String parseUUID(byte[] bytes) {
        int startByte = 2;
        boolean patternFound = false;
        while (startByte <= 5) {
            if (((int) bytes[startByte + 2] & 0xff) == 0x02 && //Identifies an iBeacon
                    ((int) bytes[startByte + 3] & 0xff) == 0x15) { //Identifies correct data length
                patternFound = true;
                break;
            }
            startByte++;
        }

        if (patternFound) {
            byte[] uuidBytes = new byte[16];
            System.arraycopy(bytes, startByte + 4, uuidBytes, 0, 16);
            String hexString = bytesToHex(uuidBytes);

            String uuid = hexString.substring(0, 8) + "-" +
                    hexString.substring(8, 12) + "-" +
                    hexString.substring(12, 16) + "-" +
                    hexString.substring(16, 20) + "-" +
                    hexString.substring(20, 32);
            return uuid;
        } else {
            return "";
        }
    }


    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * 字符转换为byte
     *
     * @param c 字符
     * @return
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * int转十六进制字符串,如果没有两位在左面加0
     *
     * @param i 十进制整型
     * @return
     */
    public static String intToHexStr(int i) {
        String hexStr = Integer.toHexString(i);

        if (hexStr.length() % 2 != 0) {
            hexStr = "0" + hexStr;
        }
        return hexStr.toUpperCase();
    }

    /**
     * 将十进制字符串转换成十六进制字符串输出，如果已有一位，前面加0
     *
     * @param s
     * @return
     */
    public static String strToHexStr(int s) {
        String string = Integer.toHexString(s);
        String str = addZeroForNumLeft(string, 2);
        return str;
    }

    /**
     * 将传入的32位字符串转换为8-4-4-4-12的模式
     *
     * @param s
     * @return
     */
    public static String toUUID(String s) {
        Object[] uuid = new Object[36];
        //传入的必须是长度为32位的字符串
        if (!TextUtils.isEmpty(s) && s.length() == 32) {
            String[] strings = s.trim().split("");
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";
            for (int i = 0; i < 37; i++) {
                if (i < 8) {
                    uuid[i] = strings[i + 1];
                } else if (i < 13 && i > 8) {
                    uuid[i] = strings[i];
                } else if (i < 18 && i > 13) {
                    uuid[i] = strings[i - 1];
                } else if (i < 23 && i > 18) {
                    uuid[i] = strings[i - 2];
                } else if (i < 36 && i > 23) {
                    uuid[i] = strings[i - 3];
                }
            }
            return StringUtils.join(uuid);
        } else {
            return null;
        }
    }

    /**
     * 将数字转换为字符串，个位数字前面加零
     *
     * @param num int型
     * @return
     */
    public static String format2LenStr(int num) {
        return (num < 10) ? "0" + num : String.valueOf(num);
    }

    /**
     * 将数字转换为字符串，个位数字前面加零
     *
     * @param num long型
     * @return
     */
    public static String format2LenStr(long num) {
        return (num < 10) ? "0" + num : String.valueOf(num);
    }

    /**
     * 将传入的字符串转换为指定位数，不足的右补零
     *
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNumRight(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

    /**
     * 将传入的字符串转换为指定位数，不足的左补零
     *
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNumLeft(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

}
