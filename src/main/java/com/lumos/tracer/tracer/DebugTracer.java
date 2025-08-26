
package com.lumos.tracer.tracer;

import java.nio.ByteBuffer;

import com.lumos.tracer.LumosTracer;

public class DebugTracer implements Tracer{
        @Override
        public void log(byte[] payload) {
                // TODO Auto-generated method stub
                LumosTracer.contexts.get().localLogs.add(new String(payload));
        }

}
