package com.test;

public class TestMain {
    public static void main(String[] args) {
        String s = Integer.toBinaryString(128);
        System.out.println(s);
        /**
         *  6<<2
         *  00000110 <<2 = 00011000 2^4+2^3 =2*2*2*2+2*2*2 = 16+8 =24
         *
         *  6>>2
         *  00000110 >>2 = 00000001 2^0 = 1
         *
         *  负数 就是 先反码即翻转过来，然后末尾补码1
         * 1111 1111 1111 1111 1111 1111 1111 1011
         */
        System.out.println(6<<2);
        System.out.println(6>>2);
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(-6>>>2);

        String hello = "Hello World! ";
        System.out.println("1->"+hello.replace(" ",""));
        hello = "  ";
        System.out.println("2->"+hello.replace(" ",""));
        hello = "";
        System.out.println("3->"+hello.replace(" ",""));
    }
}
