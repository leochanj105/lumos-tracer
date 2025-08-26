package com.lumos.tracer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class HindsightJNI {
        public static native void hindsightInit(String procName, String config);

        public static native void hindsightTracepoint(byte[] payload, int size);

        public static native void hindsightEnd();
        //returnBufferNative();

        public static native ByteBuffer switchBufferNative();

        public static native void hindsightBegin(long traceId);

        public static class HSTrace {

                public static final int BSIZE = 4096 - 32;
                public int position = 0;
                public byte[] cache = new byte[BSIZE];

                public int remaining() {
                        return cache.length - position;
                }

                public HSTrace() {
                        position = 0;
                }

                public void hs_tracecpoint(byte[] payload) {
                        if (try_write(payload))
                                return;
                        write(payload);
                }

                public boolean try_write(byte[] payload) {
                        if (payload.length > remaining())
                                return false;
                        System.arraycopy(payload, 0, cache, position, payload.length);
                        position += payload.length;
                        return true;
                }

                public void write(byte[] payload) {
                        int curr = 0;
                        while (curr < payload.length) {
                                int remaining = remaining();// content.remaining();
                                int to_write = payload.length - curr;
                                if (remaining >= to_write) {
                                        System.arraycopy(payload, curr, cache, position, to_write);
                                        position += to_write;
                                        curr = payload.length;
                                        break;
                                }
                                System.arraycopy(payload, curr, cache, position, remaining);
                                position += remaining;
                                curr += remaining;
                                hindsightTracepoint(cache, position);
                                position = 0;
                        }
                }

                // public void switchBuffer(){
                // endBuffer();
                // }

                // public void endBuffer(){
                // writeSizeToHeader();
                // content.put(cache, 0, position);
                // }
                // public void prepareBuffer() {
                // this.buffer.position(32);
                // this.buffer.order(ByteOrder.LITTLE_ENDIAN);
                // this.content = this.buffer.slice();
                // }

                // public void hs_tracecpoint(byte[] payload) {
                // if (try_write(payload))
                // return;
                // write(payload);
                // }

                // public boolean try_write(byte[] payload) {
                // if (payload.length > content.remaining())
                // return false;
                // this.content.put(payload);
                // return true;
                // }

                // public void write(byte[] payload) {
                // int curr = 0;
                // while (curr < payload.length) {
                // int remaining = content.remaining();
                // int to_write = payload.length - curr;
                // if (remaining >= to_write) {
                // content.put(payload, curr, to_write);
                // curr = payload.length;
                // break;
                // }
                // content.put(payload, curr, remaining);
                // curr += remaining;
                // switchBuffer();
                // }
                // }

                // public void switchBuffer() {
                // writeSizeToHeader();
                // this.buffer = HindsightJNI.switchBufferNative();
                // prepareBuffer();
                // }

                // public void writeSizeToHeader() {
                // int size = content.capacity() - content.remaining() + 32;
                // buffer.putInt(24, size);
                // }

                public void endTrace(){
                        // writeSizeToHeader();
                        hindsightTracepoint(cache, position);
                        position = 0;
                        HindsightJNI.hindsightEnd();
                }

                // @Override
                // public String toString() {
                // return "Trace [buffer=" + buffer + "]";
                // }

        }
}
