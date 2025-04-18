package com.lumos.tracer;

import com.lumos.tracer.tracer.CountingTracer;
import com.lumos.tracer.tracer.DebugTracer;
import com.lumos.tracer.tracer.FileTracer;
import com.lumos.tracer.tracer.NullTracer;
import com.lumos.tracer.tracer.SimulatedAsyncTracer;
import com.lumos.tracer.tracer.Tracer;
import com.lumos.tracer.tracer.XTraceTracer;

public class LumosRegister{
        // public static Tracer tracer;
        // public static void log(String s){
        //         tracer.log(s);
        // }
        public static String ltracer = "";

        public static void registerTracer() {
                ltracer = System.getProperty("Ltracer");
                registerTracer(ltracer);
        }

        public static void registerTracer(String ltracer) {
                try {
                        if (ltracer.equals("file")) {
                                LumosTracer.tracer = new FileTracer();
                        } else if (ltracer.equals("async")) {
                                SimulatedAsyncTracer stracer = new SimulatedAsyncTracer();
                                LumosTracer.tracer = stracer;
                                String lat = System.getProperty("LogLatency");
                                if (lat != null) {
                                        SimulatedAsyncTracer.LOG_LATENCY = Long.valueOf(lat);
                                }
                        } else if (ltracer.equals("debug")) {
                                LumosTracer.tracer = new DebugTracer();
                        } else if (ltracer.equals("xtrace")) {
                                LumosTracer.tracer = new XTraceTracer();
                        } else if (ltracer.equals("counting")) {
                                LumosTracer.tracer = new CountingTracer();
                        } else if (ltracer.equals("opentelemetry")) {
                                // FIXME: No OTLP for now
                        } else {
                                LumosTracer.tracer = new NullTracer();
                        }

                } catch (Throwable e) {
                        e.printStackTrace();
                }
                System.out.println("[REGISTER]");
        }
}
