package com.panda.recursion;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Kuohao {

    public List<String> generateParenthesis(int n) {
//        generate(0, 0, n, "");
        List<String> res = new LinkedList<>();
        generate2(0, 2 * n, "", res);
        System.out.println(res);
        return res;
    }

    void generate(int left, int right, int n, String s, List<String> res) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        if (left < n) {
            generate(left + 1, right, n, s + "(", res);
        }
        if (left > right) {
            generate(left, right + 1, n, s + ")", res);
        }
    }

    void generate2(int level, int n, String s, List<String> res) {
        if (level >= n) {
            Stack<Character> stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                if ('(' == s.charAt(i)) {
                    stack.push(')');
                } else if (stack.isEmpty() || stack.pop() != s.charAt(i)) {
                    return;
                }
            }
            if (stack.isEmpty()) {
                res.add(s);
            }
            return;
        }
        generate2(level + 1, n, s + "(",res);
        generate2(level + 1, n, s + ")",res);
    }

    public static void main(String[] args) {
        new Kuohao().generateParenthesis(3);
    }
}