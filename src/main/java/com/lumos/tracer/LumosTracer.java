package com.lumos.tracer;

import java.io.File;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.DocFlavor.BYTE_ARRAY;

import com.google.protobuf.ByteString;
import com.lumos.tracer.HindsightJNI.HSTrace;
import com.lumos.tracer.tracer.HindsightTracer;
import com.lumos.tracer.tracer.Tracer;

import edu.brown.cs.systems.baggage.BaggageContents;
import javassist.bytecode.ByteArray;

public class LumosTracer {
        public static ThreadLocal<ThreadContext> contexts = ThreadLocal.withInitial(() -> new ThreadContext());
        public static Tracer tracer;
        public static long XX = 0;
        public static String dir = System.getenv("LUMOS_LOG_DIR");
        public static boolean noHash = System.getProperty("NoHash") != null;
        public static String verbose = System.getProperty("verbose");
        static {
                if(verbose == null){
                        verbose = "debug";
                }

                // String ltracer = System.getProperty("Ltracer");
                // if (ltracer.equals("hs")) {
                //         System.out.println("initting done; warming up...");
                //         HindsightJNI.hindsightBegin(0);
                //         HSTrace t = new HSTrace();
                //         byte[] bs = new byte[16];
                //         // trigger JIT compilation
                //         for (int i = 0; i < 100000; i++) {
                //                 // LumosTracer.logAddressAndId(null, 0);
                //                 // LumosTracer.logTraceAndId(0, 0);
                //                 t.hs_tracecpoint(bs);
                //         }
                //         HindsightJNI.hindsightEnd();
                // }
        }
        public static void logNull(Object obj, String tag){

        }
        public static void logCounter(){
                System.out.println("end: " + System.nanoTime());
                System.out.println("counter = " + contexts.get().counter);
        }
        public static void logDebug(String tag){
                if(isRecordingOn()){
                        toggle(false);
                        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                        System.out.println("---");
                        for (StackTraceElement e : stackTraceElements) {
                                System.out.println(e.getClassName() + ":" + e.getMethodName());
                        }
                        toggle(true);
                }
                else{
                }
        }

        public static void fakelog(byte[] b){

        }


        public static void logAddressAndId(Object content, int tag) {
                // byte[] y = new byte[8];
                if (isRecordingOn()) {
                        // int N = Integer.valueOf(System.getenv("DVALUE"));
                        // long start = System.nanoTime();

                        // for (int i = 0; i < N; i++) {
                                tracer.log(Utils.getPayload(tag, System.identityHashCode(content)));
                        // }
                        // long end = System.nanoTime();
                        // System.out.println("## Addr time:" + (end - start) / N);
                }
        }

        public static void logAddress(Object content, String tag) {
                // boolean check = isRecordingOn();
                // if (tag.contains("PBHelper:")) {
                //         toggle(false);
                //         System.out.println("@@ " + check + ": " + tag);
                //         // if (tag.contains("add(")) {
                //         //         Map m = (Map) content;
                //         //         for (Object o : m.values()) {
                //         //                 // tracer.log(getPayload("hc:" + System.identityHashCode(o)));
                //         //                 // tracer.log(getPayload("str:" + o));

                //         //                 System.out.println("hc:" + System.identityHashCode(o));

                //         //                 StackTraceElement[] stack = (new Throwable()).getStackTrace();
                //         //                 for (int i = 0; i < stack.length; i++) {
                //         //                         System.out.println(stack[i].toString());
                //         //                 }
                //         //                 // System.out.println(tag);

                //         //         }
                //         // }
                //         toggle(true);
                // }
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag + "," + System.identityHashCode(content)+"\n"));

                }
        }

        public static void logTraceAndId(Object content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, (content == null ? "" : content.toString())));
                }
        }

        public static void logTrace(Object content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag + (content == null ? "" : content.toString())+"\n"));
                }
        }

        public static void logTraceAndId(int content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, content));
                }
        }

        public static void logTrace(int content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(byte content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, content));
                }
        }

        public static void logTrace(byte content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(float content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, ByteBuffer.allocate(4).putFloat(content).array()));
                }
        }

        public static void logTrace(float content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(double content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, ByteBuffer.allocate(8).putDouble(content).array()));
                }
        }

        public static void logTrace(double content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(char content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, content));
                }
        }
        public static void logTrace(char content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(char[] content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, (new String(content)).getBytes()));
                }
        }

        public static void logTrace(char[] content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + new String(content)+"\n"));
                }
        }

        public static void logTraceAndId(boolean content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, content ? 1 : 0));
                }
        }

        public static void logTrace(boolean content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        public static void logTraceAndId(long content, int tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag, content));
                }
        }

        public static void logTrace(long content, String tag) {
                if (isRecordingOn()) {
                        tracer.log(Utils.getPayload(tag +  "," + content+"\n"));
                }
        }

        // public static void tracer.log(byte[] payload) {
        //         tracer.log(payload);
        // }


        public static boolean isRecordingOn() {
                // StackTraceElement[] stack = (new Throwable()).getStackTrace();
                // return false;
                // 0 = checkValid, 1 = most recent logSysOut
                // for (int i = 2; i < stack.length; i++) {
                // StackTraceElement e = stack[i];
                // String mname = e.getMethodName();
                // if (mname.equals("logSysOut") ||
                // mname.equals("logTrace")
                // || mname.equals("println")
                // //|| (mname.equals("get") && e.getClassName().equals("ThreadLocal"))
                // // || mname.equals("<clinit>")
                // ) {
                // return false;
                // }

                // }
               return contexts.get().on;
               // return true;
        }

        public static void logNone() {
                XX += 1;
        }

        public static void readXX(){
                System.out.println(XX);
        }

        public static void addCallerBaggage(){
                String callerID = UUID.randomUUID().toString();
                // System.out.println("caller:" + callerID);
                // -2 for callerID at caller
                LumosTracer.logTraceAndId("caller-side:"+callerID, -2);
                // System.out.println(callerID);
                BaggageContents.add("Lumos", "caller", callerID);
        }

        public static void startRecording(String recName){
                if(!System.getProperty("mode").equals("on")){
                        return;
                }
                String ltracer = System.getProperty("Ltracer");
                toggle(true);
                if(ltracer.equals("hs")){
                        contexts.get().recName = recName;
                        contexts.get().recId = UUID.randomUUID();
                        contexts.get().hstrace = new HindsightJNI.HSTrace();
                        HindsightJNI.hindsightBegin(UUID.randomUUID().getLeastSignificantBits());
                        // System.out.println("%%%" + contexts.get().hstrace);
                        // -3 for start time
                        long start = System.nanoTime();
                        if (verbose.equals("performance")) {
                                // simulate the length of the id
                                // byte[] payload = long2barr(42);

                                // ByteBuffer payload = ByteBuffer.allocateDirect(8).putLong(42L);
                                // HindsightJNI.hindsightTracepoint(payload, payload.capacity());
                                // simulate the overheads
                                LumosTracer.logTraceAndId(42L,-10);
                                LumosTracer.logTraceAndId(start, -3);
                        } else {
                                // byte[] payload = recName.getBytes();
                                // ByteBuffer buf = ByteBuffer.allocateDirect(payload.length).put(payload);
                                // HindsightJNI.hindsightTracepoint(buf, payload.length);
                                LumosTracer.logTrace(recName, "recName:");
                                LumosTracer.logTrace(start + "", "START_STAMP:");
                        }
                } else {
                        if (ltracer.equals("debug")) {
                                contexts.get().stat.clear();
                                contexts.get().recId = UUID.randomUUID();
                                contexts.get().recName = recName;
                                contexts.get().localLogs.clear();
                                contexts.get().counter = 0;
                                contexts.get().start = System.nanoTime();
                                System.out.println("start rec: " + recName);
                        }
                }
                if (!ltracer.equals("async") && BaggageContents.contains(ByteString.copyFromUtf8("Lumos"),
                                ByteString.copyFromUtf8("caller"))) {
                        String callerID = BaggageContents.getStrings("Lumos", "caller").iterator().next();
                                        // .toByteArray();
                        // -1 for callerID at callee
                        LumosTracer.logTraceAndId("callee-side:"+callerID,-1);
                        // System.out.println(callerID);
                        BaggageContents.removeAll("Lumos");
                }
        }

        // public static void HSEndTrace() {
        //         contexts.get().hstrace.returnBuffer();
        // }

        public static void endRecording(){

                // -4 for end time
                long end = System.nanoTime();

                if(!System.getProperty("mode").equals("on")){
                        return;
                }
                String ltracer = System.getProperty("Ltracer");
                if (ltracer.equals("hs") && contexts.get().hstrace != null) {
                        if (verbose.equals("performance")) {
                                LumosTracer.logTraceAndId(end, -4);
                        } else {
                                LumosTracer.logTrace(end + "", "END_STAMP:");
                        }
                        toggle(false);
                        //System.out.println("ENDING " + contexts.get().recName +", " + contexts.get().recId);
                        // contexts.get().hstrace.hs_tracecpoint("ENDING....\n".getBytes());
                        //HSEndTrace();
                        // contexts.get().hstrace.end
                        contexts.get().hstrace.endTrace();
                        // HindsightJNI.hindsightEnd();
                        contexts.get().hstrace = null;
                        // HindsightJNI.hindsightEnd();
                } else {
                        toggle(false);
                        if (ltracer.equals("debug")) {
                                contexts.get().end = System.nanoTime();
                                System.out.println("[LUMOS] recName=" + contexts.get().recName + "::"
                                                + contexts.get().recId);
                                System.out.println("[LUMOS] start=" + contexts.get().start);
                                System.out.println("[LUMOS] end=" + contexts.get().end);
                                System.out.println(contexts.get().localLogs.size());

                                ThreadContext ctx = LumosTracer.contexts.get();
                                // System.out.println("counter = ");
                                output(ctx.recId, ctx.recName, ctx.localLogs);
                        }
                        // } else {
                        //         String shortName = ctx.recName.substring(1, ctx.recName.indexOf('('));
                        //         append(shortName, contexts.get().start, contexts.get().end);
                        // }
                }

        }

        public static void append(String name, long start, long end){
                File outputDir = new File(dir);
                if (!outputDir.exists()) {
                        outputDir.mkdir();
                }
                File file = new File(dir + name);
                FileWriter writer;
                // System.out.println("outputting to " + file.getName());
                try {
                        writer = new FileWriter(file, true);
                        writer.write(start + " " + end + "\n");
                        writer.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }


        public static void output(UUID id, String name, List<String> logs){
                File outputDir = new File(dir);
                if (!outputDir.exists()) {
                        outputDir.mkdir();
                }
                File file = new File(dir + id);
                FileWriter writer;
                System.out.println("outputting to " + file.getName() + ": " + name);
                try {
                        writer = new FileWriter(file);
                        writer.write("[TRACE_NAME]: " + name + System.lineSeparator());
                        for (String str : logs) {
                                writer.write(str + System.lineSeparator());
                        }
                        writer.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        public static void updateStat(String type, String inst){
                if (isRecordingOn()) {
                        toggle(false);
                        Map<String, List<String>> stat = contexts.get().stat;
                        if(!stat.containsKey(type)){
                                stat.put(type, new ArrayList<>());
                        }
                        stat.get(type).add(inst);
                        toggle(true);
                }
        }

        // public static void logSysOut()
        public static void toggle(boolean on) {
                // System.out.println("## in toggle");
                contexts.get().on = on;
                // if(!on){
                //         System.out.println("turning off");
                // }
                // else{
                //         contexts.get().on = false;

                //         System.out.println("turning on");
                //         contexts.get().on = true;
                // }
        }

        public static boolean getRR(){
                return contexts.get().on;
        }

        public static void resetCounter(){
                contexts.get().counter = 0;
                System.out.println("begin: " + System.nanoTime());
        }

}
