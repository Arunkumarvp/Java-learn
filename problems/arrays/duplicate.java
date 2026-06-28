import java.util.Scanner;
// find the duplicate in array 
public class duplicate {
    public static void main(String[] args) {
    System.out.println("find the duplicate");
    Scanner scn=new Scanner(System.in);
    int size=scn.nextInt();
    int [] arr=new int[size];
    for(int i=0;i<size;i++){
        arr[i]=scn.nextInt();
    }
    for(int i=0;i<size;i++){
        for(int j=i+1;j<arr.length;j++){
            if(arr[i]==arr[j]){
                System.out.println("duplicate:"+arr[j]);
                
            }

            }
        }
    }

    
}
