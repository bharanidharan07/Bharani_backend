package com.project.mainclass.configuration;

import java.util.HashSet;
import java.util.Set;

public class demo {
    public static int removeDuplicates(int[] nums) {
        Set <Integer> suji = new HashSet<>();
          for(int i=0;i<nums.length;i++){
              suji.add(nums[i]);
          }
          return suji.size();
    }
    public static void main(String[] args) {
        int a[] = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(a));
    }
}
