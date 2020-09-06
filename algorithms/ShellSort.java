package algorithms;
import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args){
        sort(new int[]{3,2,1,45,28,17,32});
        sort(new int[]{1,8,4,2,7,6,3,5});
        sort(new int[]{5,4,3,2,1});
    }

    private static void sort(int[] in) {
        for(int gap = in.length/2;gap>0;gap/=2){
            System.out.println(gap);
            for(int i=gap;i<in.length;i++){
                int temp = in[i];
                int j=i;
                for(;j-gap>=0;j-=gap){
                    if(in[j-gap]>temp){
                        in[j]=in[j-gap];
                    }else
                        break;
                }
                in[j]=temp;
                System.out.println(Arrays.toString(in));                                                                           
            }
        }
        System.out.println(Arrays.toString(in));
    }
}