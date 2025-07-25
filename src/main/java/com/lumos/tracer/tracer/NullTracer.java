package com.lumos.tracer.tracer;

public class NullTracer implements Tracer{

        @Override
        public void log(String msg) {
        }

        @Override
        public void log(long msg) {
        }

}
