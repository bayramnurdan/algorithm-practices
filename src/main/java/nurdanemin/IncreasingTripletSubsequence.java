package nurdanemin;
//Leet Code problem 334

import java.util.ArrayList;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int left = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > mid) {
                return true;
            } else if (num < left) {
                left = num;
            } else if (num > left && num < mid) {
                mid = num;
            }

        }
        return false;


    }
}