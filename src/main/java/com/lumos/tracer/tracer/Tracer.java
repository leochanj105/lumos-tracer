package com.lumos.tracer.tracer;

import java.nio.ByteBuffer;

public interface Tracer{
        // public void log(String msg);

        // public void log(long msg);

        public void log(ByteBuffer payload);
}
