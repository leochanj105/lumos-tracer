package com.lumos.tracer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import com.lumos.tracer.HindsightJNI.HSTrace;

public class ThreadContext{
        public boolean on = false;
        public Stack<Boolean> toggleStack = new Stack<>();
        public static int BUFFER_SIZE = 1000;
        public int head;
        public int counter = 0;
        public String recName;
        public UUID recId;
        public long start,end;
        public List<String> localLogs = new ArrayList<>();
        public static LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        public Map<String, List<String>> stat = new HashMap<String, List<String>>();
        public List<ByteBuffer> buffers = new ArrayList<>();
        public int currBuf = 0;
        public int currByte = 0;
        public HSTrace hstrace;
        public void log(Object o){
                queue.offer(o);
        }
        public static int LUMOS_BUF_SIZE;
        static{
                String s = System.getenv("LUMOS_BUF_SIZE");
                if(s!= null){
                        LUMOS_BUF_SIZE = Integer.valueOf(s);
                }
        }

}
