
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viki
 */
public class Array_Rotation {
  public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int d = scan.nextInt();
        int[] a= new int[n];
        for(int i =0;i<n;i++)
            a[i]= scan.nextInt();
 System.out.println(Arrays.toString( a));
                    int x = a[0];
                    for(int j =0;j<d;j++){
       
            for(int i =0;i<n-1;i++)
                a[i]=a[i+1];
            a[n-1]=x;
        }

        for(int i =0;i<n;i++)
            System.out.print(a[i]+" ");
}
}
