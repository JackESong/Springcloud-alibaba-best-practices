package com.springcloud.rest.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 就是给定一个字符串，然后按写竖着的 「z」的方式排列字符，就是下边的样子。
 * 然后按行的方式输出每个字符，第 0 行，第 1 行，第 2 行 ….
 */
public class code_006_Test {

    public static void main(String[] args){

    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++){
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            } //遍历到两端，改变方向
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {ret.append(row);}
        return ret.toString();
    }


}
