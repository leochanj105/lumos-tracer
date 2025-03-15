package com.lumos.tracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadContext{
        public boolean on = false;
        public Stack<Boolean> toggleStack = new Stack<>();
        // public Object[] localLog;
        public static int BUFFER_SIZE = 1000;
        public int head;
        public int counter = 0;
        public String recName;
        public UUID recId;
        public long start,end;
        public List<String> localLogs = new ArrayList<>();
        public static LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        public Map<String, List<String>> stat = new HashMap<String, List<String>>();
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
