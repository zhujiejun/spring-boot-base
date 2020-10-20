package com.zhujiejun.java.audit;

/**
 * ++i i++
 */

/**
 * iconst_1 //Push int constant onto the operand stack
 * istore_1 //Store int into local variable
 * iload_1  //Load int from local variable
 * iinc	    //Increment local variable by constant
 * ---------------------
 * int i = 1;
 * i = ++i;
 * 0: iconst_1
 * 1: istore_1
 * <p>
 * 2: iinc          1, 1
 * 5: iload_1
 * <p>
 * 6: istore_1
 * 7: return
 * ---------------------
 * * int i = 1;
 * 0: iconst_1
 * 1: istore_1
 * * i = i++;
 * 2: iload_1
 * 3: iinc          1, 1
 * 6: istore_1
 * * int j = i++;
 * 7: iload_1
 * 8: iinc          1, 1
 * 11: istore_2
 * * int k = i + ++i * i++;
 * 12: iload_1
 * 13: iinc          1, 1
 * 16: iload_1
 * 17: iload_1
 * 18: iinc          1, 1
 * 21: imul
 * 22: iadd
 * 23: istore_3
 */
public class Test001 {
    public static void main(String[] args) {
        int i = 1; //i=1
        i = i++;   //i=1
        //i = ++i; //i=2

        int j = i++;//j=1,i=2
        int k = i + ++i * i++; //k=2+3*3=2+9=11,i=3,j=1

        System.out.println("i = " + i);//i=4
        System.out.println("j = " + j);//j=1
        System.out.println("k = " + k);//k=11*/
    }
}
