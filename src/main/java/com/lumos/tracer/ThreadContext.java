package com.lumos.tracer;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadContext{
        public boolean on = false;
        public Object[] localLog;
        public static int BUFFER_SIZE = 1000;
        public int head;
        public static LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

        public void log(Object o){
                queue.offer(o);
                // if(localLog == null){
                //         localLog = new Object[BUFFER_SIZE];
                //         head = 0;
                // }
                // localLog[head]=o;
                // head  = (head + 1) % BUFFER_SIZE;
        }
}
