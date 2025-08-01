package com.lumos.tracer.tracer;

import edu.brown.cs.systems.xtrace.XTrace;
import edu.brown.cs.systems.xtrace.logging.XTraceLogger;

public class XTraceTracer implements Tracer{
        public static final XTraceLogger xtrace = XTrace.getLogger("LUMOS");

        @Override
        public void log(String msg) {
                try{
                        xtrace.log(msg);
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void log(long msg) {
                try{
                        xtrace.log(""+msg);
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }

}
