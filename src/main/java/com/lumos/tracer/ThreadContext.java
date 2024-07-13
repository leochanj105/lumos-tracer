package com.lumos.tracer;

public class ThreadContext{
        public boolean on = false;
        public Object[] localLog;
        public static int BUFFER_SIZE = 1000;
        public int head;

        public void log(Object o){
                if(localLog == null){
                        localLog = new Object[BUFFER_SIZE];
                        head = 0;
                }
                localLog[head]=o;
                head  = (head + 1) % BUFFER_SIZE;
        }
}
