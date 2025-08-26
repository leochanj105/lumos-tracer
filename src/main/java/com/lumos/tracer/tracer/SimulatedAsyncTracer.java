package com.lumos.tracer.tracer;

import java.nio.ByteBuffer;

public class SimulatedAsyncTracer implements Tracer{
        // public static long LOG_LATENCY = 40;

        public static int loop(){
                if(System.nanoTime() >100){
                        return 0;
                }
                return 1;
                // long currentTimeNano = System.nanoTime();
                // while (true) {
                //         if (System.nanoTime() - currentTimeNano >= LOG_LATENCY) {
                //                 break;
                //         }
                // }
        }


        @Override
        public void log(byte[] payload) {
                loop();
        }

}
