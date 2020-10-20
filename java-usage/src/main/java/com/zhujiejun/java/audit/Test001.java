package com.zhujiejun.java.audit;

/**
 * ++i i++
 */

/**
 * iconst_1 //Push int constant onto the operand stack
 * istore_1 //Store int into local variable from operand stack and remove the top of stack
 * iload_1  //Load int from local variable onto the operand stack
 * iinc	    //Increment local variable by constant
 * ---------------------
 * <p>int i = 1;
 * 0: iconst_1
 * 1: istore_1
 * <p>i = i++;
 * 2: iload_1
 * 3: iinc          1, 1
 * 6: istore_1
 * <p>int j = i++;
 * 7: iload_1
 * 8: iinc          1, 1
 * 11: istore_2
 * <p>int k = i + ++i * i++;
 * 12: iload_1
 * 13: iinc          1, 1
 * 16: iload_1
 * 17: iload_1
 * 18: iinc          1, 1
 * 21: imul
 * 22: iadd
 * 23: istore_3
 * <p>System.out.println("i = " + i);//i=4
 * 24: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 27: new           #3                  // class java/lang/StringBuilder
 * 30: dup
 * 31: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
 * 34: ldc           #5                  // String i =
 * 36: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 39: iload_1
 * 40: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
 * 43: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 46: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 * <p>System.out.println("j = " + j);//j=1
 * 49: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 52: new           #3                  // class java/lang/StringBuilder
 * 55: dup
 * 56: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
 * 59: ldc           #10                 // String j =
 * 61: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 64: iload_2
 * 65: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
 * 68: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 71: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 * <p>System.out.println("k = " + k);//k=11
 * 74: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 77: new           #3                  // class java/lang/StringBuilder
 * 80: dup
 * 81: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
 * 84: ldc           #11                 // String k =
 * 86: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 89: iload_3
 * 90: invokevirtual #7                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
 * 93: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 96: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 * 99: return
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
        System.out.println("k = " + k);//k=11
    }
}
