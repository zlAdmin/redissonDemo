package com.zl.until;

/**
 * 共享变量
 *
 * @author zhagnlei
 * @ProjectName: netty-websocket
 * @create 2019-09-13 17:41
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println("number是："+number);
            }
        }
    }
    public static void main(String[] arg) {
        new ReaderThread().start();
        number = 100;
        ready = true;
    }
}
