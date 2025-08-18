package com.lumos.tracer;

import java.io.File;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import com.google.protobuf.ByteString;
import com.lumos.tracer.tracer.Tracer;

import edu.brown.cs.systems.baggage.Baggage;
import edu.brown.cs.systems.baggage.BaggageContents;
import edu.brown.cs.systems.pivottracing.baggage.BaggageProtos.Bag;

public class LumosTracer {
        public static ThreadLocal<ThreadContext> contexts = ThreadLocal.withInitial(() -> new ThreadContext());
        public static Tracer tracer;
        public static long XX = 0;
        public static String dir = System.getenv("LUMOS_LOG_DIR");
        public static boolean noHash = System.getProperty("NoHash") != null;
        static {
        }
        public static void logNull(Object obj, String tag){

        }
        public static void logCounter(){
                System.out.println("end: " + System.nanoTime());
                System.out.println("counter = " + contexts.get().counter);
        }
        // public static void logIndexedTimedTrace(Object content, int index, String tag, long start, long end) {
        //         if (isRecordingOn()) {
        //                 toggle(false);
        //                 // System.out.println(tag+" before");
        //                 log(start + "," + end + "," + +index + "," + tag + "," +
        //                                 System.identityHashCode(content));
        //                 toggle(true);
        //                 // System.out.println(tag+" after");
        //         }
        // }

        // public static void logTimedTrace(Object content, String tag, long start, long end) {
        //         if (isRecordingOn()) {
        //                 toggle(false);
        //                 // System.out.println(tag+" before");
        //                 log(start + "," + end + "," + tag +  "," + System.identityHashCode(content));
        //                 // System.out.println(tag+" after");
        //                 toggle(true);
        //         }
        // }

        // public static void logEmptyTimedTrace(String tag, long start, long end) {
        //         // boolean ison = contexts.get().on;
        //         // toggle(false);
        //         // System.out.println("$$ " + ison);
        //         // toggle(ison);
        //         if (isRecordingOn()) {
        //                 toggle(false);
        //                 // System.out.println(tag+" before in empty time");
        //                 // try{
        //                 log(start + "," + end + "," + tag);
        //                 // }
        //                 // catch(Exception e){
        //                 //         e.printStackTrace();
        //                 // }
        //                 // System.out.println(tag+" after in empty time");
        //                 toggle(true);
        //         }
        // }
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
        public static void logDebug2(String tag, long start, long end){
                if(isRecordingOn()){
                        System.out.println(tag+":"+start+","+end);
                }
        }
        // public static void logClass(Object content, String tag){
        //         if (isRecordingOn()) {
        //                 emitLog(tag +  "," + content.getClass());
        //         }
        // }
        public static byte[] long2barr(long value) {
                return new byte[] {
                                (byte) (value >>> 56),
                                (byte) (value >>> 48),
                                (byte) (value >>> 40),
                                (byte) (value >>> 32),
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte)  value
                };
        }

        public static byte[] int2barr(int value) {
                return new byte[] {
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte)  value
                };
        }

        public static byte[] getPayload(String s){
                int length = s.length();
                byte[] lpart = int2barr(length);
                byte[] content = s.getBytes();
                byte[] payload = new byte[lpart.length + content.length];
                System.arraycopy(lpart, 0, payload, 0, lpart.length);
                System.arraycopy(content, 0, payload, lpart.length, content.length);
                return payload;
        }

        public static byte[] getPayload(long id, String content){
                byte[] idpart = long2barr(id);
                int length = content.length();
                byte[] lpart = int2barr(length);
                byte[] cpart = content.getBytes();
                byte[] payload = new byte[idpart.length + lpart.length + cpart.length];
                System.arraycopy(idpart, 0, payload, 0, idpart.length);
                System.arraycopy(lpart, 0, payload, idpart.length, lpart.length);
                System.arraycopy(cpart, 0, payload, idpart.length + lpart.length, cpart.length);
                return payload;
        }

        public static byte[] getPayload(long id, long value){
                return new byte[] {
                                (byte) (id >>> 56),
                                (byte) (id >>> 48),
                                (byte) (id >>> 40),
                                (byte) (id >>> 32),
                                (byte) (id >>> 24),
                                (byte) (id >>> 16),
                                (byte) (id >>> 8),
                                (byte) id,
                                // (byte) 8,
                                (byte) (value >>> 56),
                                (byte) (value >>> 48),
                                (byte) (value >>> 40),
                                (byte) (value >>> 32),
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte) value
                };
        }

        public static byte[] getPayload(long id, int value){
                return new byte[] {
                                (byte) (id >>> 56),
                                (byte) (id >>> 48),
                                (byte) (id >>> 40),
                                (byte) (id >>> 32),
                                (byte) (id >>> 24),
                                (byte) (id >>> 16),
                                (byte) (id >>> 8),
                                (byte) id,
                                // (byte) 4,
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte) value
                };
        }

        public static byte[] getPayload(long id, byte[] value){
                byte[] idpart = new byte[] {
                                (byte) (id >>> 56),
                                (byte) (id >>> 48),
                                (byte) (id >>> 40),
                                (byte) (id >>> 32),
                                (byte) (id >>> 24),
                                (byte) (id >>> 16),
                                (byte) (id >>> 8),
                                (byte) id
                };
                int length = value.length;
                byte[] lpart = int2barr(length);
                byte[] res = new byte[idpart.length + lpart.length + value.length];
                res[0] = (byte) (id >>> 56);
                res[1] = (byte) (id >>> 48);
                res[2] = (byte) (id >>> 40);
                res[3] = (byte) (id >>> 32);
                res[4] = (byte) (id >>> 24);
                res[5] = (byte) (id >>> 16);
                res[6] = (byte) (id >>> 8);
                res[7] = (byte) id;
                System.arraycopy(lpart, 0, res, 8, lpart.length);
                System.arraycopy(value, 0, res, 8 + lpart.length, value.length);
                return value;
        }


        public static void logAddressAndId(Object content, long tag){
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, System.identityHashCode(content)));
                }
        }

        public static void logAddress(Object content, String tag){
                if (isRecordingOn()) {
                        emitLog(getPayload(tag + "," + System.identityHashCode(content)));
                }
        }

        public static void logTraceAndId(Object content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, (content == null ? "" : content.toString())));
                }
        }

        public static void logTrace(Object content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag + (content == null ? "" : content.toString())));
                }
        }

        public static void logTraceAndId(int content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, content));
                }
        }

        public static void logTrace(int content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(byte content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, content));
                }
        }

        public static void logTrace(byte content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(float content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, ByteBuffer.allocate(4).putFloat(content).array()));
                }
        }

        public static void logTrace(float content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(double content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, ByteBuffer.allocate(8).putDouble(content).array()));
                }
        }

        public static void logTrace(double content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(char content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, content));
                }
        }
        public static void logTrace(char content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(char[] content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, (new String(content)).getBytes()));
                }
        }

        public static void logTrace(char[] content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + new String(content)));
                }
        }

        public static void logTraceAndId(boolean content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, content ? 1 : 0));
                }
        }

        public static void logTrace(boolean content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void logTraceAndId(long content, long tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag, content));
                }
        }

        public static void logTrace(long content, String tag) {
                if (isRecordingOn()) {
                        emitLog(getPayload(tag +  "," + content));
                }
        }

        public static void emitLog(byte[] payload) {
                tracer.log(payload);
        }


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
                LumosTracer.logTraceAndId(callerID, -2);
                BaggageContents.add("Lumos", "caller", callerID);
        }

        public static void startRecording(String recName){
                String ltracer = System.getProperty("Ltracer");
                if(ltracer.equals("hs")){
                        HindsightJNI.hindsightBegin(UUID.randomUUID().getLeastSignificantBits());
                        byte[] payload = recName.getBytes();
                        HindsightJNI.hindsightTracepoint(payload, payload.length);
                }
                else{
                        contexts.get().stat.clear();
                        contexts.get().recId = UUID.randomUUID();
                        contexts.get().recName = recName;
                        contexts.get().localLogs.clear();
                        contexts.get().counter = 0;
                        contexts.get().start = System.nanoTime();
                        System.out.println("start rec: " + recName);
                }
                // System.out.println("start rec: " + recName);
                if (BaggageContents.contains(ByteString.copyFromUtf8("Lumos"),
                                ByteString.copyFromUtf8("caller"))) {
                        byte[] callerID = BaggageContents.get("Lumos", "caller").iterator().next().toByteArray();
                        // -1 for callerID at callee
                        LumosTracer.logTraceAndId(callerID, -1);
                        BaggageContents.removeAll("Lumos");
                }
                // -3 for start time
                LumosTracer.logTraceAndId(System.nanoTime(),-3);
                toggle(true);
        }

        public static void endRecording(){
                toggle(false);
                // -4 for end time
                LumosTracer.logTraceAndId(System.nanoTime(),-4);
                String ltracer = System.getProperty("Ltracer");
                if (ltracer.equals("hs")) {
                        HindsightJNI.hindsightEnd();
                } else {
                        contexts.get().end = System.nanoTime();
                        System.out.println("[LUMOS] recName=" + contexts.get().recName + "::" + contexts.get().recId);
                        System.out.println("[LUMOS] start=" + contexts.get().start);
                        System.out.println("[LUMOS] end=" + contexts.get().end);
                        System.out.println(contexts.get().localLogs.size());

                        ThreadContext ctx = LumosTracer.contexts.get();
                        System.out.println("counter = " + ctx.counter);
                        // if (LumosRegister.ltracer.equals("async")) {
                        // String ltracer = System.getProperty("Ltracer");
                        if (ltracer != null && ltracer.equals("debug")) {
                                output(ctx.recId, ctx.recName, ctx.localLogs);
                        } else {
                                String shortName = ctx.recName.substring(1, ctx.recName.indexOf('('));
                                append(shortName, contexts.get().start, contexts.get().end);
                        }
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
