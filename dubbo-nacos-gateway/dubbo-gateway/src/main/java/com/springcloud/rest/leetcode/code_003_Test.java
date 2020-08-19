package com.springcloud.rest.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，找到没有重复字符的最长子串，返回它的长度。
 * abcabcbb
 * 返回 abc
 */
public class code_003_Test {

    public static void main(String[] args){

    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;//保存当前得到满足条件的子串的最大值
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j <= n; j++){//之所以 j<= n，是因为我们子串是 [i,j),左闭右开
                if (allUnique(s, i, j)){
                    ans = Math.max(ans, j - i); //更新 ans
                }
            }
        }

        return ans;
    }
    public boolean allUnique(String str, int start, int end) {
        Set<Character> set = new HashSet<>();//初始化 hash set
        for (int i = start; i < end; i++) {//遍历每个字符
            Character ch = str.charAt(i);
            if (set.contains(ch)) return false; //判断字符在不在 set 中
            set.add(ch);//不在的话将该字符添加到 set 里边
        }
        return true;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}
