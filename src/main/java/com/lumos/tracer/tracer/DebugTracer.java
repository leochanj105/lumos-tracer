
package com.lumos.tracer.tracer;

import com.lumos.tracer.LumosTracer;

public class DebugTracer implements Tracer{
        public static long LOG_LATENCY = 40;
        @Override
        public void log(String msg) {
                LumosTracer.contexts.get().localLogs.add(msg);
        }

}
