
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viki
 */
public class Array_left_Shift {
public static void main(String args[]){
   int[] array_nums = {20, 30, 40};
	System.out.println(Arrays.toString(array_nums));
	int[] new_array_nums = {array_nums[1], array_nums[2], array_nums[0]};
	System.out.println(Arrays.toString(new_array_nums));
}
}
