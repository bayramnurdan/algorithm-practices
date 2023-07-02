package nurdanemin;

import java.util.Arrays;

//LeetCode Problem Number 2305. Fair Distribution of Cookies
public class FairDistributionOfCookies {
    static int result = Integer.MAX_VALUE;
    public  static int distributeCookies(int[] cookies, int numberOfChildren) {
        int[] distributions = new int[numberOfChildren];
        distribute(cookies, distributions, 0);
        return result;


    }
    public static void distribute(int[] cookies,  int[] currentDistribution, int cookieIndex){
        if (cookieIndex == cookies.length){
            int max = currentDistribution[0];
            for (int dist: currentDistribution ){
                max = Math.max(max, dist);
            }
            result = Math.min(max, result);
            return;
        }

        for (int i=0; i<currentDistribution.length; i++){
            currentDistribution[i] += cookies[cookieIndex];
            distribute(cookies, currentDistribution, cookieIndex+1);
            currentDistribution[i] -= cookies[cookieIndex];
        }
    }





    }