import java.util.*;


class FrequencyOfArray {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter the size of the array");
        int n = scn.nextInt();
        int[] arr = new int[n];
        int[] fr = new int[arr.length];
        // array inputing into
        System.out.println("enter the elements of the array");
        for (int i = 0; i < n; ++i) {
            arr[i] = scn.nextInt();
        }
        // here we need to find the frequencyOfArray
        int visited = -1, count=1;
        for (int j = 0;  j< n; ++j) {
            for(int k=j+1; k<n; ++k){
                if(arr[j]==arr[k]){
                    count++;
                    arr[k]=visited;
                }
            }
            while(arr[j]!=visited){
                fr[j]=count;
            }
            System.out.println("Arrays of visisted elements are now");
            System.out.println("================================");
            for (int i=0; i<fr.length;++i){
                System.out.println("elements"+arr[i]+ "|"+fr[i]);
            }
        }

    }

}