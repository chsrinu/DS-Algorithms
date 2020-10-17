package mustdocodingquestions.strings;

import java.util.Stack;

/**Given a string, recursively remove adjacent duplicate characters from the string. 
 * The output string should not have any adjacent duplicates. See following examples.
 * Input: azxxzy 
Output: ay 
First “azxxzy” is reduced to “azzy”. 
The string “azzy” contains duplicates, 
so it is further reduced to “ay”.
Input: geeksforgeeg 
Output: gksfor 
First “geeksforgeeg” is reduced to 
“gksforgg”. The string “gksforgg” 
contains duplicates, so it is further 
reduced to “gksfor”.
Input: caaabbbaacdddd 
Output: Empty String
Input: acaaabbbacdddd 
Output: acac 
 */
public class RemoveAdjacentDuplicates {
    public static void main(String[] args){
        String in = "zaaz";
        checkResult(bruteforceApproach(in),"","Bruteforce Approach",in);
        checkResult(bruteforce_usingStack(in),"","Bruteforce_stack Approach",in);
        in = "abzz";
        checkResult(bruteforceApproach(in),"ab","Bruteforce Approach",in);
        checkResult(bruteforce_usingStack(in),"ab","Bruteforce_stack Approach",in);

        in = "zzz";
        checkResult(bruteforceApproach(in),"","Bruteforce Approach",in);
        checkResult(bruteforce_usingStack(in),"","Bruteforce_stack Approach",in);

        in = "zzab";
        checkResult(bruteforceApproach(in),"ab","Bruteforce Approach",in);
        checkResult(bruteforce_usingStack(in),"ab","Bruteforce_stack Approach",in);

        in = "bzaazb";
        checkResult(bruteforceApproach(in),"","Bruteforce Approach",in);
        checkResult(bruteforce_usingStack(in),"","Bruteforce_stack Approach",in);

    }

    private static void checkResult(String actual, String expected, String approach, String in) {
        String status = actual.equals(expected)?"Passed":"Failed";
        System.out.println(approach+" : Test with input "+in+" "+status);
        if(!actual.equals(expected)){
            System.out.println("Actual "+actual+" "+actual.length());
            System.out.println("Expected "+expected);
        }
    }
    /**Time complexity: O(n) */
    public static String bruteforceApproach(String s) {
        if(s.isEmpty())
            return s;
        char[] ar = s.toCharArray();
        boolean containDuplicates = false;
        for(int i=0;i<ar.length-1;){
            if(ar[i]==ar[i+1]){
                containDuplicates=true;
                int j=i+1;
                do{
                    ar[j]='0';
                    j++;
                }while(j<ar.length && ar[i]==ar[j]);
                ar[i]='0';
                if(j>=ar.length-1)
                    break;
                i=j;
            }else{
                i++;
            }   
        }
        String s1 = new String(ar).replaceAll("0", "");
        if(containDuplicates){
            return bruteforceApproach(s1);
        }
        return s1;
    }
    /**Time complexity: O(n) 
     * a bit cleaner than the array based approach
    */
    public static String bruteforce_usingStack(String in){
        Stack<Character> st = new Stack<>();
        boolean removePreviousTop = false;
        for(int i=0;i<in.length();i++){
            if(st.isEmpty() || st.peek()!=in.charAt(i)){
                if(removePreviousTop){
                    st.pop();
                    removePreviousTop = false;
                }
                st.push(in.charAt(i));
            }else{
                removePreviousTop = true;
                continue;
            }
        }
        if(removePreviousTop)
            st.pop();
        String updated = convertStacktoString(st);
        if(updated.length()==in.length())
            return updated;
        else
            return bruteforce_usingStack(updated);
    }

    private static String convertStacktoString(Stack<Character> st) {
        StringBuffer sb = new StringBuffer();
        Character[] ar = st.toArray(Character[]::new);
        for(int i=ar.length-1;i>=0;i--){
            sb.append(ar[i]);
        }
        return sb.toString();
    }
}
