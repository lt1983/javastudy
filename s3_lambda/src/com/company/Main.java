package com.company;

/**
 * @author Tars
 */
public class Main {

    public static void main(String[] args) {
        repeat(10, () -> System.out.println("Hello world!"));
        repeat2(10, i -> System.out.println("Hello world! " + i));
    }

    private static void repeat(int n,Runnable action) {
        for (int  i = 0; i<n; i++) {
            action.run();
        }
    }

    private static void repeat2(int n,IntConsumer action) {
        for (int  i = 0; i<n; i++) {
            action.accept(i);
        }
    }
    Throwable
}

