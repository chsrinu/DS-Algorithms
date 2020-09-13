package mustdocodingquestions;
import java.util.Arrays;
/**
 * https://practice.geeksforgeeks.org/problems/-rearrange-array-alternately/0/
 * 
 * Given a sorted array of positive integers. Your task is to rearrange  the array elements alternatively i.e first element should be max value, second should be min value, third should be second max, fourth should be second min and so on...

Note: O(1) extra space is allowed. Also, try to modify the input array as required.
 */
public class RearrageArrayAlternatively {
    public static void main(String[] args){
        bruteForceApproach(new int[]{5,8,12,3,1,13}, new int[]{13,1,12,3,8,5});
        testOnSortedList(new int[]{1,2,3,4,5,6}, new int[]{6,1,5,2,4,3});
    }
    public static void bruteForceApproach(int[] ar, int[] expectedOutput){
        int n = ar.length%2!=0?ar.length-1:ar.length;
        String inputArray = Arrays.toString(ar);
        boolean testFail = false;
        for(int i=0,j=1;i<n;i+=2,j+=2){
            int max = i;
            int min = i;
            for(int m=i+1;m<ar.length;m++){
                if(ar[m]>ar[max]){
                    max = m;
                }else if(ar[m]<ar[min]){
                    min = m;
                }
            }
            swap(max,i,ar);
            swap(min,j,ar); 
        }
        for(int i=0;i<expectedOutput.length;i++){
            if(expectedOutput[i]!=ar[i]){
                testFail=true;
                break;
            }
        }
        String testCaseStatus = testFail? "Failed": "Passed";
        System.out.println("Test with input "+inputArray+" "+testCaseStatus);
        if(testFail){
            System.out.println("Expected: "+Arrays.toString(expectedOutput));
            System.out.println("Actual: "+Arrays.toString(ar));
        }
    }
    public static void swap(int i,int j,int[] ar){
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
    /**When the input array is already sorted, that's the actual question */
    public static void testOnSortedList(int[] ar,int[] expected){
        int max = ar[ar.length-1]+1;
        String inputArray = Arrays.toString(ar);
        for(int i=0,minPos=0,maxPos=ar.length-1;i<ar.length;){
            if(i%2==0){
                if(maxPos<i)
                    ar[i]= ar[i++]*max+(ar[maxPos--]/max);
                else
                    ar[i]= ar[i++]*max+ar[maxPos--];
            }else{
                ar[i]= ar[i++]*max+(ar[minPos++]/max);
            }                       
        }
        
        boolean testCaseFailed = false;
        for(int i=0;i<expected.length;i++){
            ar[i] = ar[i]%max;
            if(expected[i]!=ar[i]){
                testCaseFailed = true;
                break;
            }
        }
        String testCaseStatus = testCaseFailed? "Failed": "Passed";
        System.out.println("Test with input "+inputArray+" "+testCaseStatus);
        if(testCaseFailed){
            System.out.println("Expected: "+Arrays.toString(expected));
            System.out.println("Actual: "+Arrays.toString(ar));
        }  
    }
}
