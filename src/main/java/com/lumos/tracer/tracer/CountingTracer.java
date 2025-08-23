
// package com.lumos.tracer.tracer;

// import java.nio.ByteBuffer;

// import com.lumos.tracer.LumosTracer;

// import edu.brown.cs.systems.xtrace.XTrace;
// import edu.brown.cs.systems.xtrace.logging.XTraceLogger;

// public class CountingTracer implements Tracer{
//         public static final XTraceLogger xtrace = XTrace.getLogger("LUMOS");

//         @Override
//         public void log(ByteBuffer payload) {
//                 LumosTracer.contexts.get().counter++;
//         }

// }
