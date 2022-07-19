package com.panda.双指针;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/7/12
 * @description:
 */
public class Sum3 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        HashSet<List<Integer>> result = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                int target = -nums[i] - nums[j];
                // map.get(target) > j 防止重复包含自己
                if (map.containsKey(target) && map.get(target) > j) {
                    result.add(Arrays.asList(nums[i], target, nums[j]));
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) throws IOException {
        int[] nums = new int[]{1, 2, -2, -1};
        System.out.println(new Sum3().threeSum(nums));

    }
}
