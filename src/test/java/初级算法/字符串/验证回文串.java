package 初级算法.字符串;

/**
 * @ClassName: 验证回文串.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-08-05
 * @Version: 1.0
 */
public class 验证回文串 {
    public boolean isPalindrome(String s) {
        char[] charArray = s.toLowerCase().toCharArray();

        int low = 0, hi = charArray.length - 1;
        while (low < hi) {

//            只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (low < hi && !Character.isLetterOrDigit(charArray[low])) {
                low++;
            }
//            只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (low < hi && !Character.isLetterOrDigit(charArray[hi])) {
                hi--;
            }

            if (charArray[low] != charArray[hi]) {
                return false;
            }
            low++;
            hi--;
        }

        return true;

    }


    public boolean isPalindrome2(String s) {

        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reverse = new StringBuffer(str).reverse().toString();
        return str.equals(reverse);
    }
}
