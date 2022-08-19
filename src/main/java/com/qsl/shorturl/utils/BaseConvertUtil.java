package com.qsl.shorturl.utils;

/**
 * 进制转换工具类
 *
 * @author DanielQSL
 */
public class BaseConvertUtil {

    /**
     * 在进制表示中的字符集合
     */
    private final static char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private BaseConvertUtil() {
    }

    /**
     * 将10进制的数字转换到62进制字符
     *
     * @param num 数字
     * @return 62进制字符
     */
    public static String to62BaseStr(long num) {
        return toOtherBaseStr(num, 62);
    }

    /**
     * 将10进制的数字转换到其他进制字符
     *
     * @param num  数字
     * @param base 多少进制
     * @return 62进制字符
     */
    public static String toOtherBaseStr(long num, int base) {
        int charPos = 32;
        char[] buf = new char[32];
        while ((num / base) > 0) {
            buf[--charPos] = DIGITS[(int) (num % base)];
            num /= base;
        }
        buf[--charPos] = DIGITS[(int) (num % base)];
        return new String(buf, charPos, (32 - charPos));
    }

}
