package 初级算法.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: 字符串中的第一个唯一字符.java
 * @Author: jjl
 * @Description: 字符串中的第一个唯一字符
 * @Date: 2021-08-05
 * @Version: 1.0
 */
public class 字符串中的第一个唯一字符 {
    public int firstUniqChar(String s) {

        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < chars.length; i++) {
            if (1 == map.get(chars[i])) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
