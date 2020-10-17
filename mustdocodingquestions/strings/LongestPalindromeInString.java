package mustdocodingquestions.strings;

/**
 * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * 
 * Given a string, find the longest substring which is palindrome. For example,
 * 
 * Input: Given string :"forgeeksskeegfor", Output: "geeksskeeg"
 * 
 * Input: Given string :"Geeks", Output: "ee"
 * 
 */
public class LongestPalindromeInString {
    public static void main(String[] args) {
        String in = "abcgoogabc";
        checkResult(bruteforceApproach(in), "goog", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "goog", "Optimized approach", in);
        checkResult(dp_approach(in), "goog", "Dynamic programming approach", in);

        in = "aa";
        checkResult(bruteforceApproach(in), "aa", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "aa", "Optimized approach", in);
        checkResult(dp_approach(in), "aa", "Dynamic programming approach", in);

        in = "aabb";
        checkResult(bruteforceApproach(in), "aa", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "aa", "Optimized approach", in);
        checkResult(dp_approach(in), "aa", "Dynamic programming approach", in);

        in = "aabbb";
        checkResult(bruteforceApproach(in), "bbb", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "bbb", "Optimized approach", in);
        checkResult(dp_approach(in), "bbb", "Dynamic programming approach", in);

        in = "abcdbb";
        checkResult(bruteforceApproach(in), "bb", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "bb", "Optimized approach", in);
        checkResult(dp_approach(in), "bb", "Dynamic programming approach", in);

        in = "a";
        checkResult(bruteforceApproach(in), "a", "Bruteforce approach", in);
        checkResult(optimized_approach(in), "a", "Optimized approach", in);
        checkResult(dp_approach(in), "a", "Dynamic programming approach", in);
    }

    private static void checkResult(String actual, String expected, String apparoach, String in) {
        String status = actual.equals(expected) ? "Passed" : "Failed";
        System.out.println(apparoach + ": Test with input " + in + " " + status);
        if (!actual.equals(expected)) {
            System.out.println("Actual: " + actual);
            System.out.println("Expected: " + expected);
        }
    }

    /** Time complexity: O(n^2) */
    public static String bruteforceApproach(String in) {
        String maxLenString = in.substring(0, 1);
        for (int i = 0; i < in.length(); i++) {
            for (int j = in.length(); j > i + 1; j--) {
                String sub = in.substring(i, j);
                String reverse = new StringBuilder(sub).reverse().toString();
                if (sub.equals(reverse)) {
                    if (sub.length() > maxLenString.length())
                        maxLenString = sub;
                }
                if (sub.length() <= maxLenString.length())
                    break;
            }
        }
        return maxLenString;
    }

    /** Time complexity: O(n^2) */
    public static String optimized_approach(String in) {
        int start = 0, end = 0;
        for (int i = 1; i < in.length(); i++) {
            // one center palindrome <=> odd length palindrome string
            for (int j = i - 1, k = i + 1; j >= 0 && k < in.length(); j--, k++) {
                if (in.charAt(j) == in.charAt(k)) {
                    int currentLength = k - j + 1;
                    int maxLength = end - start + 1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        end = k;
                        start = j;
                    } else if (currentLength == maxLength) {
                        if (j < start) {
                            start = j;
                            end = k;
                        }
                    }
                } else
                    break;
            }
            // 2 centered palindrome <=> even length palindrome string
            for (int j = i - 1, k = i; j >= 0 && k < in.length(); j--, k++) {
                if (in.charAt(j) == in.charAt(k)) {
                    int currentLength = k - j + 1;
                    int maxLength = end - start + 1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        end = k;
                        start = j;
                    } else if (currentLength == maxLength) {
                        if (j < start) {
                            start = j;
                            end = k;
                        }
                    }
                } else
                    break;
            }
        }
        return in.substring(start, end + 1);
    }
    /*Time complexity O(n^2)*/
    public static String dp_approach(String in){
        //x axis is end of substring
        //y axis is starting point of substring
        int[][] dp = new int[in.length()][in.length()];
        int maxLength = 1;
        int start = 0,end = 0;
        //substrings of length 1
        for(int i=0;i<dp.length;i++){
            dp[i][i]=1;
        }
        //subsrings of length 2
        //i is the index where the substring ends
        for(int i=0,j=1;j<dp.length;i++,j++){
            if(in.charAt(i)==in.charAt(j)){
                dp[i][j] = 1;
                if(maxLength<2){
                    maxLength =2;
                    start=i;
                    end=j;
                }
            }
        }
        int substring_length = 3;
        while(substring_length<=in.length()){
            for(int start_i=0,end_j=substring_length-1;end_j<in.length();start_i++,end_j++){
                if(in.charAt(start_i)==in.charAt(end_j) && dp[start_i+1][end_j-1]==1){
                    dp[start_i][end_j] = 1; 
                    if(maxLength<substring_length){
                        maxLength = substring_length;
                        start = start_i;
                        end = end_j;
                    }    
                }
            }
            substring_length++;
        }
        return in.substring(start, end+1);
    }

}