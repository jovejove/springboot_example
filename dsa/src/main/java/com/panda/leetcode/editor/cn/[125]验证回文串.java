//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 
//
// 示例 1: 
//
// 
//输入: "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
// 
//
// 示例 2: 
//
// 
//输入: "race a car"
//输出: false
//解释："raceacar" 不是回文串
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// 字符串 s 由 ASCII 字符组成 
// 
// Related Topics 双指针 字符串 👍 537 👎 0



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        /*
            方法1：
            1.正则出去特殊字符且都转成大写或小写字符
            2.字符反转
            3.字符串比较

            方法2：双指针
            1.循环判断首尾字符是否是字母或者数字，否：continue
            2.是：比较收尾指针的值是否相等，不相等返回false。相等则继续循环首指针向后移动，尾指针向前移动再比较，直到left>=right退出
            3.循环外默认返回true
         */



        // repalce special char
//        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//        // reverse
//        StringBuilder reverse = new StringBuilder(s).reverse();
//        // compare
//        return reverse.toString().equalsIgnoreCase(s);

        // 双指针夹逼
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
