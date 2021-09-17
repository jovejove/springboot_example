package 初级算法.字符串;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: 有效的字母异位词.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-08-05
 * @Version: 1.0
 */
public class 有效的字母异位词 {

    public boolean isAnagram(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        Map<Character, Integer> a = new HashMap<>();
        Map<Character, Integer> b = new HashMap<>();

        if (sc.length != tc.length) {
            return false;
        }

        for (char c : sc) {
            a.put(c, a.getOrDefault(c, 0) + 1);
        }

        for (char c : tc) {
            b.put(c, b.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> c : a.entrySet()) {
            if (!c.getValue().equals(b.get(c.getKey()))) {
                return false;
            }
        }

        return true;
    }


    public boolean isAnagram2(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        Arrays.sort(sc);
        Arrays.sort(tc);

        return Arrays.equals(sc, tc);
    }
}
