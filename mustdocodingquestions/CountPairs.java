package mustdocodingquestions;
import java.util.Arrays;
/**
 * https://practice.geeksforgeeks.org/problems/number-of-pairs/0/
 * 
 * Given two arrays X[] and Y[] of size M and N respectively. 
 * Find number of pairs such that xy > yx where x is an element from X[] and y 
 * is an element from Y[].
 * 
 * 
 */
public class CountPairs {
    public static void main(String[] args){
        bruteForceApproach(new int[]{5,2,12}, new int[]{4,1,6}, 5);
        test(new int[]{5,2,12}, new int[]{4,3,6}, 2);
        test(new int[]{1,2,4},new int[]{2,3,6},2);
    }
    public static void bruteForceApproach(int[] x, int[] y,int expected){
        System.out.println("--Bruteforce approach--");
        int count = 0;
        for(int i=0;i<x.length;i++){
            for(int j=0;j<y.length;j++){
                if(Math.pow(x[i],y[j])>Math.pow(y[j],x[i])){
                    count++;
                }                   
            }
        }
        String testCaseStatus = count == expected? "Passed":"Failed";
        System.out.println("Test with input "+Arrays.toString(x)+", "+Arrays.toString(y)+" "+testCaseStatus);
        if(testCaseStatus.equals("Failed")){
            System.out.println("Expected: "+expected);
            System.out.println("Actual: "+count);
        }
    }
    /**
     * For all Y>X this condition satisifies X^Y>Y^X but there are some exceptional
     * cases even though Y>X
     * 1)x=2, y=3 or 4(x^y<y^x)
     * 2)x=1, y=any number other than zero does not satisfy the condition but least
     * element in any array is 1 so no point of checking for 1
     * 3)x=3 and y=2 even though y<x fails still the condition x^y>y^x holds true only 
     * in this case.     
     * */
    public static void test(int[] x, int[] y, int expected){
        System.out.println("--Efficient approach--");
        Arrays.sort(x);
        Arrays.sort(y);
        int count = 0;
        for(int i=0;i<x.length;i++){
            if(x[i]!=1){
                for(int j=0;j<y.length;j++){
                    if((x[i]==3 && y[j]==2||y[j]>x[i]) && !(x[i]==2 && (y[j]==3||y[j]==4))){
                        count+=(y.length-j);
                        break;
                    }
                }
            }
        }
        String testCaseStatus = count == expected? "Passed":"Failed";
        System.out.println("Test with input "+Arrays.toString(x)+", "+Arrays.toString(y)+" "+testCaseStatus);
        if(testCaseStatus.equals("Failed")){
            System.out.println("Expected: "+expected);
            System.out.println("Actual: "+count);
        }
    }
}
