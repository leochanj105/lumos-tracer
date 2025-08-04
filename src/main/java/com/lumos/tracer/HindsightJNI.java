package com.lumos.tracer;

public class HindsightJNI {
        public static native void hindsightInit(String procName, String config);

        public static native void hindsightTracepoint(byte[] payload, int size);

        public static native void hindsightBegin(long traceId);

        public static native void hindsightEnd();
}
