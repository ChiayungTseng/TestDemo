package javabasic;

import java.math.BigDecimal;

public class StringDemo {

    public static void main(String[] args) {
        int i = 0x10;
        System.out.println(i);
        BigDecimal bigDecimal = new BigDecimal(1.224);
        System.out.println(bigDecimal.setScale(2, 4).toString());

    }

    public void kmp(char[] s,char[] p){
        int[] next = new int[p.length];

    }
}
