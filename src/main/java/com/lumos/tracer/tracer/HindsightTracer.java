package com.lumos.tracer.tracer;

import java.nio.ByteBuffer;

import com.lumos.tracer.HindsightJNI;

public class HindsightTracer implements Tracer{
        public static String hsAgentName = System.getenv("HS_AGENT_NAME");
        public HindsightTracer(){
                System.load("/home/jingyuan/lumos/lumos-tracer/jni/libHS.so");
                System.out.println("initting hs...");
                HindsightJNI.hindsightInit(hsAgentName, "/etc/hindsight_conf/default.conf");
                System.out.println("initting done");
        }

        @Override
        public void log(ByteBuffer payload) {
                // int N = Integer.valueOf(System.getenv("DVALUE"));
                // ByteBuffer buf = ByteBuffer.allocateDirect(payload.length).put(payload);
                // long start = System.nanoTime();
                // for (int i = 0; i < N; i++) {
                // HindsightJNI.hindsightTracepoint(payload, payload.capacity());
                // }
                // long end = System.nanoTime();
                // System.out.println("## " + payload.length + ", " + (end - start) / N);
                //loop(payload);
        }

        public static int loop(ByteBuffer payload){
                if(System.nanoTime() >100){
                        return 0;
                }
                return 11;
        }

}
