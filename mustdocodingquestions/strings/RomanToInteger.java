package mustdocodingquestions.strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    static Map<Character,Integer> hm = new HashMap<>();
    public static void main(String[] args){
        hm.put('I',1);
        hm.put('V',5);
        hm.put('X',10);
        hm.put('L',50);
        hm.put('C',100);
        hm.put('D',500);
        hm.put('M',1000);

        String in = "XLII";
        checkResult(bruteforceApproach(in),42,"Bruteforce approach",in);
        in = "L";
        checkResult(bruteforceApproach(in),50,"Bruteforce approach",in);
        in = "LXIV";
        checkResult(bruteforceApproach(in),64,"Bruteforce approach",in);
        in = "MXCIV";
        checkResult(bruteforceApproach(in),1094,"Bruteforce approach",in);
        in = "IM";
        checkResult(bruteforceApproach(in),999,"Bruteforce approach",in); 
        in = "CM";
        checkResult(bruteforceApproach(in),900,"Bruteforce approach",in);        
    }

    private static void checkResult(int actual, int expected, String approach, String in) {
        String status = actual == expected?"Passed":"Failed";
        System.out.println(approach+" :Test with input "+in+" "+status);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int bruteforceApproach(String in) {
        char[] ar = in.toCharArray();
        int sum = 0, i = 0;
        for(;i<ar.length-1;i++){
            if(hm.get(ar[i])<hm.get(ar[i+1])){
                sum +=hm.get(ar[i+1])-hm.get(ar[i]);
                i++;       
            }else{
                sum+=hm.get(ar[i]);
            }
        }
        if(i<ar.length){
            sum+=hm.get(ar[ar.length-1]);
        }
        return sum;
    }
}
