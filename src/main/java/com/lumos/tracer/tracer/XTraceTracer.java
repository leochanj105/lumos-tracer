// package com.lumos.tracer.tracer;

// import java.nio.ByteBuffer;

// import edu.brown.cs.systems.xtrace.XTrace;
// import edu.brown.cs.systems.xtrace.logging.XTraceLogger;

// public class XTraceTracer implements Tracer{
//         public static final XTraceLogger xtrace = XTrace.getLogger("LUMOS");

//         @Override
//         public void log(ByteBuffer payload) {
//                 try{
//                         xtrace.log(payload+"");
//                 } catch (Throwable e) {
//                         e.printStackTrace();
//                 }
//         }

// }
