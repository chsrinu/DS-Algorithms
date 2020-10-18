package mustdocodingquestions.strings;

/**
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 * 
 * Write a function to check whether two given strings are anagram of each other 
 * or not. An anagram of a string is another string that contains the same characters, 
 * only the order of characters can be different. For example, “abcd” and “dabc” are 
 * an anagram of each other.
 * 
 * 
 */
public class Anagrams {
    public static void main(String[] args){
        String s1 = "abc";
        String s2 = "bac";
        checkResult(bruteforceApproach(s1,s2),true,"Bruteforce Approach",s1+":"+s2);

        s1 = "a";
        s2 = "b";
        checkResult(bruteforceApproach(s1,s2),false,"Bruteforce Approach",s1+":"+s2);
        
        s1 = "aabc";
        s2 = "abc";
        checkResult(bruteforceApproach(s1,s2),false,"Bruteforce Approach",s1+":"+s2);

    }

    private static void checkResult(boolean actual, boolean expected, String approach, String in) {
        String status = actual==expected?"Passed":"Failed";
        System.out.println(approach+" :Test with input "+in+" "+status);
        if(actual!=expected){
            System.out.println("Actual "+actual);
            System.out.println("Expected "+expected);
        }
    }
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static boolean bruteforceApproach(String s1, String s2) {
        if(s1.length()!=s2.length())
            return false;
        char[] hashArray1 = new char[26];
        for(int i=0;i<s1.length();i++){
            int index = s1.charAt(i)-'a';
            hashArray1[index]++;
        }
        for(int i=0;i<s2.length();i++){
            int index = s2.charAt(i)-'a';
            hashArray1[index]--;
        }
        for(int i:hashArray1){
            if(i!=0)
                return false;
        }
        return true;
    }
    
}
