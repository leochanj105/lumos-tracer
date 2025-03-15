
package com.lumos.tracer.tracer;

import com.lumos.tracer.LumosTracer;

import edu.brown.cs.systems.xtrace.XTrace;
import edu.brown.cs.systems.xtrace.logging.XTraceLogger;

public class CountingTracer implements Tracer{
        public static final XTraceLogger xtrace = XTrace.getLogger("LUMOS");

        public void logCounter() {
                try {
                        xtrace.log("" + LumosTracer.contexts.get());
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void log(String msg) {
                LumosTracer.contexts.get().counter++;
        }

}
