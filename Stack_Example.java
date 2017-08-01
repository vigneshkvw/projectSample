
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viki
 */
public class Stack_Example {

  public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        Scanner in = new Scanner(System.in);
       
        int max = 0;
       
           try {
			while (true) {
				System.out.println("1.push\t2.delete\t3print Max.");
            int type = in.nextInt();

            switch(type){
                case 1:
                    int num = in.nextInt();
                    stack.push(num);
                    if(num > max){
                        max = num;
                    }
                    System.out.println(stack);
                break;
                case 2:
                                    num = stack.pop();
                    if(num == max){
                        max = 0;
                        for(int j = 0; j < stack.size(); j++){
                            if(stack.elementAt(j) > max){
                               max = stack.elementAt(j);
                            }
                        }
                    }
                    System.out.println(stack);
                break;
                case 3:
                System.out.println(max);
                break;

            }
            }
		} catch (Exception e) {
			System.out.println("SELF THROWN EXCEPTION IS--->" + e);
		}finally {
			in.close();
		}
        }
   
}       