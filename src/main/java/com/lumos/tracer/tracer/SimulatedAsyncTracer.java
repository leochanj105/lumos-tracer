package com.lumos.tracer.tracer;

public class SimulatedAsyncTracer implements Tracer{
        public static long LOG_LATENCY = 40;
        @Override
        public void log(String msg) {
                loop();
        }
        @Override
        public void log(long msg) {
                loop();
        }

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

}
