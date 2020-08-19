package com.springcloud.rest.leetcode;

/**
 *把 int 转成字符串，然后判断是否是回文串做就可以了，缺点是需要额外的空间存储字符串，当然题目也告诉了不能这样，所以 pass 。
 */
public class code_009_Test {

    public static void main(String[] args){

    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10)
                return 0;
            if (rev < Integer.MIN_VALUE / 10)
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = reverse(x);
        return x == rev;
    }

}
