package com.lumos.tracer;

import com.lumos.tracer.tracer.CountingTracer;
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

        public static void registerTracer() {
                String ltracer = System.getProperty("Ltracer");
                registerTracer(ltracer);
        }

        public static void registerTracer(String ltracer) {
                try {
                        if (ltracer.equals("file")) {
                                LumosTracer.tracer = new FileTracer();
                        } else if (ltracer.equals("async")) {
                                LumosTracer.tracer = new SimulatedAsyncTracer();
                        } else if (ltracer.equals("xtrace")) {
                                LumosTracer.tracer = new XTraceTracer();
                        } else if (ltracer.equals("counting")) {
                                LumosTracer.tracer = new CountingTracer();
                        } else if (ltracer.equals("opentelemetry")) {
                        } else{
                                LumosTracer.tracer = new NullTracer();
                        }

                } catch (Throwable e) {
                        e.printStackTrace();
                }
                System.out.println("[REGISTER]");
        }
}
