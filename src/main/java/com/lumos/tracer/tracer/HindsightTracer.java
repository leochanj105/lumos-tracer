package com.lumos.tracer.tracer;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lumos.tracer.HindsightJNI;
import com.lumos.tracer.LumosTracer;
import com.lumos.tracer.HindsightJNI.HSTrace;

public class HindsightTracer implements Tracer{
        public static String hsAgentName = System.getenv("HS_AGENT_NAME");
        public static AtomicBoolean b = new AtomicBoolean(false);
        public HindsightTracer(){
                System.load("/home/jingyuan/lumos/lumos-tracer/jni/libHS.so");
                System.out.println("initting hs...");
                HindsightJNI.hindsightInit(hsAgentName, "/etc/hindsight_conf/default.conf");
        }

        public void warmup(){
                if (LumosTracer.verbose.equals("performance")) {
                        System.out.println("warm up...");
                        HSTrace t = new HSTrace();
                        byte[] bs = new byte[8];
                        HindsightJNI.hindsightBegin(0);
                        for (int i = 0; i < 100000; i++) {
                                LumosTracer.logAddressAndId(null, 0);
                                LumosTracer.logTraceAndId("",0);
                                LumosTracer.logTraceAndId(0, 0);
                                LumosTracer.logTraceAndId(0L, 0);
                                LumosTracer.logTraceAndId(true, 0);
                        }
                        HindsightJNI.hindsightEnd();
                        System.out.println("warm up done");
                }
        }

        @Override
        public void log(byte[] payload) {
                // int N = Integer.valueOf(System.getenv("DVALUE"));
                // boolean bb = b.get();
                // if (!bb) {
                //         bb = b.getAndSet(true);
                // }
                // if (!bb) {
                //         for (int i = 0; i < 100000; i++) {
                                LumosTracer.contexts.get().hstrace.hs_tracecpoint(payload);
                //         }
                // }
                // long start = System.nanoTime();
                // for (int i = 0; i < N; i++) {
                        // LumosTracer.contexts.get().hstrace.hs_tracecpoint(payload);
                // }
                // long end = System.nanoTime();
                // System.out.println("## " + payload.length+ ", " + (end - start) / N);
                //loop(payload);
        }


        public static int loop(ByteBuffer payload){
                if(System.nanoTime() >100){
                        return 0;
                }
                return 11;
        }

}
