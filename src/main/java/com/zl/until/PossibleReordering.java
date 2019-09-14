package com.zl.until;

/**
 * @author zhagnlei
 * @ProjectName: netty-websocket
 * @create 2019-09-14 21:45
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start(); other.start();
        one.join(); one.join();
        System.out.println("(" + x + "," + y + ")");
    }
}
