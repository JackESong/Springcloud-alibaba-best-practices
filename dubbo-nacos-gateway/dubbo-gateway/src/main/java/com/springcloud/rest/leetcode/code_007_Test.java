package com.springcloud.rest.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *很简单，就是输入整数，输出它的倒置。
 *
 */
public class code_007_Test {

    public static void main(String[] args){
        System.out.println(54321/10);
        System.out.println(54321%10);
    }

    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE ) return 0;
        return (int)rev;
    }

}
