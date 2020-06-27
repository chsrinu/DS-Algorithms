public class FindSubArrayInArray {

    public static void main(String[] args){
        FindSubArrayInArray finder = new FindSubArrayInArray();
        int[] ar = new int[]{3,2,3,3,1,3,3,4,3,3,3,2};
        int[] subArray = new int[]{3,3,2};

        finder.printIndices(finder.findSubArrayInArray_bruteForce(ar,subArray),"Bruteforce");

        finder.printIndices(finder.findSubArrayInArray_2Pointer(ar,subArray),"Efficient one");
    }

    /**Time complexity:
     * Best case : Ω(m*n)
     * Average case : θ(m*n) 
     * Worst case : O(m*n) */
    public int[] findSubArrayInArray_bruteForce(int[] ar,int[] subArray){
        if(ar.length>0 && subArray.length>0){
            int[][] subArrayList = new int[ar.length-subArray.length+1][subArray.length];
            for(int i=0;i<=ar.length-subArray.length;i++){
                for(int j=i,k=0;j<i+subArray.length;j++,k++){
                    subArrayList[i][k] = ar[j];
                }
            }
            for(int i=0;i<subArrayList.length;i++){
                boolean found =true;
                for(int j=0;j<subArray.length && found;j++){
                    if(subArrayList[i][j]==subArray[j])
                        continue;
                    else
                        found=false;
                }
                if(found)
                    return new int[]{i,i+subArray.length-1};
            }
        }
        return new int[]{};
    }

    /**Time complexity:
     * Best case : Ω(min(m,n))
     * Average case : θ(m+n) 
     * Worst case : O(m*n) */
    public int[] findSubArrayInArray_2Pointer(int[] ar,int[] subArray){
        int start =-1;
        if(subArray.length>0){
            for(int i=0,j=0;i<ar.length;){
                if(ar[i]==subArray[j]){
                    if(start==-1)
                        start=i;
                    i++;
                    j++;
                    if(j==subArray.length)
                        return new int[]{start,start+subArray.length-1};
                }else{
                    j=0;
                    if(start!=-1)
                        i=start+1;
                    else
                        i++;                        
                    start=-1;
                }
            }
        }
        return new int[]{};
    }

    public void printIndices(int[] indices, String approach){
        System.out.println("Approach :"+approach);
        if(indices.length>0){
            System.out.println("SubArray exists, starts@"+indices[0]+" and ends @"+indices[1]);
        }else{
            System.out.println("SubArray not found");
        }
    }
}