
import java.util.ArrayList;
import java.util.Scanner;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viki
 */
public class Arrary {

public static void main(String[] args) {
      ArrayList<String> al = new ArrayList<String>();
      al.add("1");
      al.add("2");
      al.add("3");
      al.add("4");
      al.add("5");
      al.add("6");
      al.add("7");

      System.out.println("Index of '1': "+al.indexOf("1"));
      System.out.println("Index of '22': "+al.indexOf("22"));
      System.out.println("Index of '33': "+al.indexOf("33"));
      System.out.println("Index of '4': "+al.indexOf("4"));
  }

}
