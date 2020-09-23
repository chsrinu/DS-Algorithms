package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-platforms/0
 * 
 * Given arrival and departure times of all trains that reach a railway station.
 *  Your task is to find the minimum number of platforms required for the railway 
 * station so that no train waits.

Note: Consider that all the trains arrive on the same day and leave on the same day.
Also, arrival and departure times will not be same for a train, but we can have 
arrival time of one train equal to departure of the other. In such cases, we need 
different platforms, i.e at any given instance of time, same platform can not be 
used for both departure of a train and arrival of another.

Example:
Input:
2
6 
0900  0940 0950  1100 1500 1800
0910 1200 1120 1130 1900 2000
3
0900 1100 1235
1000 1200 1240 

Output:
3
1

Explanation:
Testcase 1: Minimum 3 platforms are required to safely arrive and depart all trains.

Time complexity of bruteForceApproach: O(n^2)
Time complexity of bruteForceApproach: O(nlogn)


*/

public class MinimumPlatforms {
    public static void main(String[] args){
        String[] arr = new String[]{"0850","0900","0901","1310"};
        String[] dep = new String[]{"0859","0910","0930","1330"};
        checkResult(bruteforceApproach(arr,dep),2,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),2,"Optimized approach",new String[][]{arr,dep});

        /**Zero collision Test */
        arr = new String[]{"0001","0430","1500"};
        dep = new String[]{"0030","1430","1600"};
        checkResult(bruteforceApproach(arr,dep),1,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),1,"Optimized approach",new String[][]{arr,dep});

        /**One collision Test */
        arr = new String[]{"0001","0430","1500","1310"};
        dep = new String[]{"0030","1430","1600","1330"};
        checkResult(bruteforceApproach(arr,dep),2,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),2,"Optimized approach",new String[][]{arr,dep});
        /**Two collision Test */
        arr = new String[]{"0430","1310","1400"};
        dep = new String[]{"1430","1330","1430"};
        checkResult(bruteforceApproach(arr,dep),2,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),2,"Optimized approach",new String[][]{arr,dep});

        /**Two collision Test */
        arr = new String[]{"0430","1405","1400"};
        dep = new String[]{"1430","1410","1430"};
        checkResult(bruteforceApproach(arr,dep),3,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),3,"Optimized approach",new String[][]{arr,dep});

        /**Two collision Test */
        arr = new String[]{"0430","1400","1310"};
        dep = new String[]{"1430","1430","1330"};
        checkResult(bruteforceApproach(arr,dep),2,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),2,"Optimized approach",new String[][]{arr,dep});

        /**Testcase from geeks */
        arr = new String[]{"0900","0940", "0950", "1100", "1500", "1800"};
        dep = new String[]{"0910", "1200", "1120", "1130", "1900", "2000"};
        checkResult(bruteforceApproach(arr,dep),3,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),3,"Optimized approach",new String[][]{arr,dep});

        /**Testcase from geeks */
        arr = new String[]{"0900", "1100", "1235"};
        dep = new String[]{"1000","1200" ,"1240"};
        checkResult(bruteforceApproach(arr,dep),1,"Bruteforce approach",new String[][]{arr,dep});
        checkResult(optimizedApproach(arr,dep),1,"Optimized approach",new String[][]{arr,dep});

    }

    public static int bruteforceApproach(String[] arr,String[] dep){
        int maxCollision = 0;
        for(int i=1;i<arr.length;i++){
            int count = 1;
            String t2_arr = arr[i];
            String t2_dep = dep[i];
            for(int j=i-1;j>=0;j--){
                String t1_dep = dep[j];
                String t1_arr = arr[j];
                if(t2_arr.compareTo(t1_dep)<=0 && t2_dep.compareTo(t1_arr)>=0){
                    count++;
                }
            }
            maxCollision = Math.max(maxCollision,count);
        }
        return maxCollision;
    }

    public static int optimizedApproach(String[] arr, String[] dep){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int count = 0,i=0,j=0,result =0;
        while(i<arr.length && j<dep.length){
            if(arr[i].compareTo(dep[j])<=0){
                count++;
                i++;
            }else{
                count--;
                j++;
            }
            result = Math.max(result,count);                        
        }
        return result;
    }

    public static void checkResult(int actual, int expected,String approach,String[][] input){
        String result = actual==expected?"Passed":"Failed";
        System.out.println(approach+": Test with input arr: "+Arrays.toString(input[0])+" dep:"+Arrays.toString(input[1])+" "+result);
        if(actual!=expected){
            System.out.println("Actual :"+actual);
            System.out.println("Expected :"+ expected);
        }
    }
}
