package com.celi.system;

/**
 * @Author jiangshengjun
 * @Date 2024/1/15
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        String[] a = new String[]{"1"};
        try {
            throw new RuntimeException("123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("234");
    }
}
