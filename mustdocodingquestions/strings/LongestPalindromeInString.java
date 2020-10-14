package mustdocodingquestions.strings;

/**
https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/

Given a string, find the longest substring which is palindrome.
For example,

Input: Given string :"forgeeksskeegfor", 
Output: "geeksskeeg"

Input: Given string :"Geeks", 
Output: "ee"

 */
public class LongestPalindromeInString{
    public static void main(String[] args){
        String in = "abcgoogabc";
        checkResult(bruteforceApproach(in),"goog","Bruteforce approach",in);
        checkResult(optimized_approach(in),"goog","Optimized approach",in);

        in = "aa";
        checkResult(bruteforceApproach(in),"aa","Bruteforce approach",in);
        checkResult(optimized_approach(in),"aa","Optimized approach",in);

        in = "aabb";
        checkResult(bruteforceApproach(in),"aa","Bruteforce approach",in);
        checkResult(optimized_approach(in),"aa","Optimized approach",in);

        in = "aabbb";
        checkResult(bruteforceApproach(in),"bbb","Bruteforce approach",in);
        checkResult(optimized_approach(in),"bbb","Optimized approach",in);

        in = "abcdbb";
        checkResult(bruteforceApproach(in),"bb","Bruteforce approach",in);
        checkResult(optimized_approach(in),"bb","Optimized approach",in);

    }
    
    private static void checkResult(String actual, String expected, String apparoach, String in) {
        String status = actual.equals(expected)?"Passed":"Failed";
        System.out.println(apparoach+": Test with input "+in+" "+status);
        if(!actual.equals(expected)){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);    
        }
    }

    /** Time complexity: O(n^2) */
    public static String bruteforceApproach(String in){
        String maxLenString = in.substring(0,1);
        for(int i=0;i<in.length();i++){
            for(int j=in.length();j>i+1;j--){
                String sub = in.substring(i,j);
                String reverse = new StringBuilder(sub).reverse().toString();
                if(sub.equals(reverse)){
                    if(sub.length()>maxLenString.length())
                        maxLenString = sub;
                }
                if(sub.length()<=maxLenString.length())
                    break;
            }
        }
        return maxLenString;
    }
    /**Time complexity: O(n) */
    public static String optimized_approach(String in){
        int start=0,end=0;
        for(int i=1;i<in.length();i++){
            //one center palindrome
            for(int j=i-1,k=i+1;j>=0 && k<in.length();j--,k++){
                if(in.charAt(j)==in.charAt(k)){
                    int currentLength = k-j+1;
                    int maxLength = end-start+1;
                    if(currentLength>maxLength){
                        maxLength = currentLength;
                        end=k;
                        start=j;
                    }else if(currentLength == maxLength){
                        if(j<start){
                            start=j;
                            end=k;
                        }
                    }
                }else
                    break;
            }
            //2 centered palindrome
            if(in.charAt(i)==in.charAt(i-1)){
                int currentLength_1 = i-(i-1)+1;
                int maxLength_1 = end-start+1;
                if(currentLength_1>maxLength_1){
                    maxLength_1 = currentLength_1;
                    end=i;
                    start=i-1;
                }else if(currentLength_1 == maxLength_1){
                    if(i-1<start){
                        start=i-1;
                        end=i;
                    }
                }
                for(int j=i-2,k=i+1;j>=0 && k<in.length();j--,k++){
                    if(in.charAt(j)==in.charAt(k)){
                        int currentLength = k-j+1;
                        int maxLength = end-start+1;
                        if(currentLength>maxLength){
                            maxLength = currentLength;
                            end=k;
                            start=j;
                        }else if(currentLength == maxLength){
                            if(j<start){
                                start=j;
                                end=k;
                            }
                        }
                    }else
                        break;
                }
            }
            
        }
        return in.substring(start, end+1);                        
    }

}