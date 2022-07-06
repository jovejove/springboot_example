package com.panda.core.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/7/5
 * @description:
 */
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        generate(0, nums, ans, new ArrayList<>());
        return ans;
    }

    private void generate(int level, int[] nums, List<List<Integer>> ans, List<Integer> tempList) {
        if (level == nums.length) {
            // why not tempList? tempList local value
            ans.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(nums[level]);
        generate(level + 1, nums, ans, tempList);
        tempList.remove(tempList.size() - 1);
        generate(level + 1, nums, ans, tempList);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> subsets = new Solution().subsets(nums);
        System.out.println(subsets);
    }

}
