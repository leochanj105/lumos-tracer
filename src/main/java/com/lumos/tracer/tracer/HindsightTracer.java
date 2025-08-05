package com.lumos.tracer.tracer;

import com.lumos.tracer.HindsightJNI;

public class HindsightTracer implements Tracer{
        public HindsightTracer(){
                System.load("/home/jingyuan/lumos/lumos-tracer/jni/libHS.so");
                HindsightJNI.hindsightInit("lumos_hs_test", "/etc/hindsight_conf/default.conf");
        }
        @Override
        public void log(String msg) {
                byte[] payload = (msg + "\n").getBytes();
                HindsightJNI.hindsightTracepoint(payload, payload.length);
        }

        @Override
        public void log(long msg) {
                throw new UnsupportedOperationException("Unimplemented method 'log'");
        }

}
