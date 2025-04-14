package com.lumos.tracer.tracer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import com.lumos.tracer.LumosTracer;
import com.lumos.tracer.ThreadContext;

public class SimulatedAsyncTracer implements Tracer{
        public static long LOG_LATENCY = 40;
        @Override
        public void log(String msg) {
                //LumosTracer.contexts.get().localLogs.add(msg);
                long currentTimeMillis = System.nanoTime();
                while (true) {
                        if (System.nanoTime() - currentTimeMillis >= LOG_LATENCY) {
                                break;
                        }
                }
                //System.out.println(msg);
        }

}
