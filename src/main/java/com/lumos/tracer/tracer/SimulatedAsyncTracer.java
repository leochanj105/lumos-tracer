package com.lumos.tracer.tracer;

import com.lumos.tracer.LumosTracer;

public class SimulatedAsyncTracer implements Tracer{

        @Override
        public void log(String msg) {
                LumosTracer.contexts.get().log(msg);
        }

}
