package mustdocodingquestions.strings;
/**
 * https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * 
 * 
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement 
 * of the elements of an ordered list S into a one-to-one correspondence with S itself. 
 * A string of length n has n! permutation.
Source: Mathword(http://mathworld.wolfram.com/Permutation.html)

Below are the permutations of string ABC.
ABC ACB BAC BCA CBA CAB
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PermutationOfString {
    static List<String> result = new ArrayList<>();
    public static void main(String[] args){
        String in = "abc";
        HashSet<String> expected = new HashSet<>(Arrays.asList(new String[]{"abc","acb","bac","bca","cab","cba"}));
        backTracking_approach(in.toCharArray(),0);
        checkResult(expected,"Backtracking approach",in);
        result.clear();
        in = "abcd";
        expected = new HashSet<>(Arrays.asList(new String[]{"abcd","abdc","acbd","acdb","adbc","adcb","bacd","badc","bcad","bcda","bdac","bdca",
    "cabd","cadb","cbad","cbda","cdab","cdba","dabc","dacb","dbac","dbca","dcab","dcba"}));
        backTracking_approach(in.toCharArray(),0);
        checkResult(expected,"Backtracking approach",in);
        result.clear();
    }

    private static void checkResult(HashSet<String> expected, String approach, String in) {
        boolean isFailed = false;
        if(expected.size() == result.size()){
            for(int i=0;i<result.size();i++){
                if(!expected.contains(result.get(i))){
                    isFailed = true;
                }else
                    expected.remove(result.get(i));
            }
        }else{
            isFailed = true;
        }
        
        String status = isFailed?"Failed":"Passed";
        System.out.println(approach+": Test with input "+in+" "+status);
        if(isFailed){
            System.out.println("Actual "+result.toString());
            System.out.println("Expected "+Arrays.toString(expected.stream().toArray()));
        }
    }
    /**Time complexity: O(n!) */
    private static void backTracking_approach(char[] ar,int index) {
        if(index==ar.length){
            result.add(new String(ar));
        }else{
            for(int i=index;i<ar.length;i++){
                swap(i,index,ar);
                backTracking_approach(ar, index+1);
                swap(i,index,ar);
            }
        }
    }

    public static void swap(int a, int b, char[] ar){
        char temp = ar[a];
        ar[a]=ar[b];
        ar[b]= temp;
    }
}
