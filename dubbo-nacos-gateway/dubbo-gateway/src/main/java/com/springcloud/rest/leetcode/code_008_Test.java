package com.springcloud.rest.leetcode;

/**
 *将一个字符串转为整型。
 */
public class code_008_Test {

    public static void main(String[] args){

    }

    public int myAtoi(String str) {
        int sign = 1;
        int ans = 0, pop = 0;
        boolean hasSign = false; //代表是否开始转换数字
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-' && !hasSign) {
                sign = -1;
                hasSign = true;
                continue;
            }
            if (str.charAt(i) == '+' && !hasSign) {
                sign = 1;
                hasSign = true;
                continue;
            }
            if (str.charAt(i) == ' ' && !hasSign) {
                continue;
            }
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                hasSign = true;
                pop = str.charAt(i) - '0';
                //和上道题判断出界一个意思只不过记得乘上 sign 。
                if (ans * sign > Integer.MAX_VALUE / 10 || (ans * sign == Integer.MAX_VALUE / 10 && pop * sign > 7))
                    return 2147483647;
                if (ans * sign < Integer.MIN_VALUE / 10 || (ans * sign == Integer.MIN_VALUE / 10 && pop * sign < -8))
                    return -2147483648;
                ans = ans * 10 + pop;
            } else {
                return ans * sign;
            }
        }
        return ans * sign;
    }

}
