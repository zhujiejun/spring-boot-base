package com.zhujiejun.java.audit;

/**
 * ++i i++
 */

/**
 * iconst_1 //Push int constant onto the operand stack
 * istore_1 //Store int into local variable from operand stack and remove the top of stack
 * iload_1  //Load int from local variable onto the operand stack
 * iinc	    //Increment local variable by constant
 * ------------------------------------------------
 * Constant pool:
 * #1 = Methodref          #13.#36        // java/lang/Object."<init>":()V
 * #2 = Fieldref           #37.#38        // java/lang/System.out:Ljava/io/PrintStream;
 * #3 = Class              #39            // java/lang/StringBuilder
 * #4 = Methodref          #3.#36         // java/lang/StringBuilder."<init>":()V
 * #5 = String             #40            // i =
 * #6 = Methodref          #3.#41         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * #7 = Methodref          #3.#42         // java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
 * #8 = Methodref          #3.#43         // java/lang/StringBuilder.toString:()Ljava/lang/String;
 * #9 = Methodref          #44.#45        // java/io/PrintStream.println:(Ljava/lang/String;)V
 * #10 = String             #46            // j =
 * #11 = String             #47            // k =
 * #12 = Class              #48            // com/zhujiejun/java/audit/Test001
 * #13 = Class              #49            // java/lang/Object
 * #14 = Utf8               <init>
 * #15 = Utf8               ()V
 * #16 = Utf8               Code
 * #17 = Utf8               LineNumberTable
 * #18 = Utf8               LocalVariableTable
 * #19 = Utf8               this
 * #20 = Utf8               Lcom/zhujiejun/java/audit/Test001;
 * #21 = Utf8               main
 * #22 = Utf8               ([Ljava/lang/String;)V
 * #23 = Utf8               args
 * #24 = Utf8               [Ljava/lang/String;
 * #25 = Utf8               i
 * #26 = Utf8               I
 * #27 = Utf8               j
 * #28 = Utf8               k
 * #29 = Utf8               StackMapTable
 * #30 = Class              #24            // "[Ljava/lang/String;"
 * #31 = Class              #49            // java/lang/Object
 * #32 = Class              #50            // java/lang/Throwable
 * #33 = Utf8               MethodParameters
 * #34 = Utf8               SourceFile
 * #35 = Utf8               Test001.java
 * #36 = NameAndType        #14:#15        // "<init>":()V
 * #37 = Class              #51            // java/lang/System
 * #38 = NameAndType        #52:#53        // out:Ljava/io/PrintStream;
 * #39 = Utf8               java/lang/StringBuilder
 * #40 = Utf8               i =
 * #41 = NameAndType        #54:#55        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * #42 = NameAndType        #54:#56        // append:(I)Ljava/lang/StringBuilder;
 * #43 = NameAndType        #57:#58        // toString:()Ljava/lang/String;
 * #44 = Class              #59            // java/io/PrintStream
 * #45 = NameAndType        #60:#61        // println:(Ljava/lang/String;)V
 * #46 = Utf8               j =
 * #47 = Utf8               k =
 * #48 = Utf8               com/zhujiejun/java/audit/Test001
 * #49 = Utf8               java/lang/Object
 * #50 = Utf8               java/lang/Throwable
 * #51 = Utf8               java/lang/System
 * #52 = Utf8               out
 * #53 = Utf8               Ljava/io/PrintStream;
 * #54 = Utf8               append
 * #55 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
 * #56 = Utf8               (I)Ljava/lang/StringBuilder;
 * #57 = Utf8               toString
 * #58 = Utf8               ()Ljava/lang/String;
 * #59 = Utf8               java/io/PrintStream
 * #60 = Utf8               println
 * #61 = Utf8               (Ljava/lang/String;)V
 * ------------------------------------------------
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
 * <p>synchronized
 * 99: ldc           #12                 // class com/zhujiejun/java/audit/Test001
 * 101: dup
 * 102: astore        4
 * 104: monitorenter
 * 105: iinc          1, 1
 * 108: iload_1
 * 109: istore_1
 * 110: aload         4
 * 112: monitorexit
 * 113: goto          124
 * 116: astore        5
 * 118: aload         4
 * 120: monitorexit
 * 121: aload         5
 * 123: athrow
 * 124: return
 *
 * Exception table:
 *          from    to  target type
 *            105   113   116   any
 *            116   121   116   any
 *       LineNumberTable:
 *         line 70: 0
 *         line 71: 2
 *         line 74: 7
 *         line 75: 12
 *         line 77: 24
 *         line 78: 49
 *         line 79: 74
 *         line 81: 99
 *         line 82: 105
 *         line 83: 110
 *         line 84: 124
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0     125     0  args   [Ljava/lang/String;
 *             2     123     1     i   I
 *            12     113     2     j   I
 *            24     101     3     k   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255 //full_frame
 *           offset_delta = 116
 *           locals = [ class "[Ljava/lang/String;", int, int, int, class java/lang/Object ]
 *           stack = [ class java/lang/Throwable ]
 *         frame_type = 250 //chop
 *           offset_delta = 7
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

        synchronized (Test001.class) {
            i = ++i;
        }
    }
}
