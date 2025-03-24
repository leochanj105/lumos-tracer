package com.lumos.tracer;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import com.lumos.tracer.tracer.Tracer;

public class LumosTracer {
        public static ThreadLocal<ThreadContext> contexts = ThreadLocal.withInitial(() -> new ThreadContext());
        public static Tracer tracer;

        public static String dir = "/home/jingyuan/hdfsstuff/lumoslogs/";
        static {
        }
        public static void logNull(Object obj, String tag){

        }
        public static void logCounter(){
                System.out.println("end: " + System.nanoTime());
                System.out.println("counter = " + contexts.get().counter);
        }
        public static void logIndexedTimedTrace(Object content, int index, String tag, long start, long end) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(start + "," + end + "," + +index + "," + tag + "," +
                                        System.identityHashCode(content));
                        toggle(true);
                        // System.out.println(tag+" after");
                }
        }

        public static void logTimedTrace(Object content, String tag, long start, long end) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(start + "," + end + "," + tag +  "," + System.identityHashCode(content));
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logEmptyTimedTrace(String tag, long start, long end) {
                // boolean ison = contexts.get().on;
                // toggle(false);
                // System.out.println("$$ " + ison);
                // toggle(ison);
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before in empty time");
                        // try{
                        log(start + "," + end + "," + tag);
                        // }
                        // catch(Exception e){
                        //         e.printStackTrace();
                        // }
                        // System.out.println(tag+" after in empty time");
                        toggle(true);
                }
        }
        public static void logDebug(String tag){
                if(isRecordingOn()){
                        toggle(false);
                        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                        System.out.println("---");
                        for (StackTraceElement e : stackTraceElements) {
                                System.out.println(e.getClassName() + ":" + e.getMethodName());
                        }
                        // if(tag.length() > 0){
                        //         throw new RuntimeException("STOP");
                        // }
                        toggle(true);
                }
                else{
                        // System.out.println(tag);
                        // StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                        // System.out.println("!!!");
                        // for (StackTraceElement e : stackTraceElements) {
                        //         System.out.println(e.getClassName() + ":" + e.getMethodName());
                        // }
                }
        }
        public static void logDebug2(String tag, long start, long end){
                if(isRecordingOn()){
                        toggle(false);
                        System.out.println(tag+":"+start+","+end);
                        toggle(true);
                }
                // else{
                //         System.out.println(tag + ":"+start+","+end+" ??");
                // }
        }
        public static void logClass(Object content, String tag){
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content.getClass());
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logAddress(Object content, String tag){
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + System.identityHashCode(content));
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(Object content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(int content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(byte content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(float content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(double content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(char content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(char[] content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + System.identityHashCode(content));
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(boolean content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" after");
                        toggle(true);
                }
        }

        public static void logTrace(long content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        // System.out.println(tag+" before");
                        log(tag +  "," + content);
                        // System.out.println(tag+" before");
                        toggle(true);
                }
        }

        public static void log(String s) {
                // logger.info(Thread.currentThread().getId() + "," + s);
                // contexts.get().log(s);
                // System.out.println("!!! " + s);
                //
                // System.out.println(s + " before");
                tracer.log(s);
                // System.out.println(s + " after");
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
        }

        public static void startRecording(String recName){
                contexts.get().stat.clear();
                contexts.get().recId = UUID.randomUUID();
                contexts.get().recName = recName;
                contexts.get().localLogs.clear();
                contexts.get().start = System.nanoTime();
                toggle(true);
        }

        public static void endRecording(){
                toggle(false);
                contexts.get().end = System.nanoTime();
                //contexts.get().stat.get(recname).add(System.nanoTime()+"");
                System.out.println("[LUMOS] recName=" + contexts.get().recName +"::" + contexts.get().recId);
                System.out.println("[LUMOS] start=" + contexts.get().start);
                System.out.println("[LUMOS] end=" + contexts.get().end);
                System.out.println(contexts.get().localLogs.size());

                ThreadContext ctx = LumosTracer.contexts.get();
                output(ctx.recId, ctx.recName, ctx.localLogs);
                // Map<String, List<String>> stat = contexts.get().stat;
                // for(String s: stat.keySet()){
                //         System.out.println("[LUMOS] " + s + ": " + stat.get(s).size());
                // }
                // for(String s: stat.keySet()){
                //         System.out.println("-------------------------");
                //         System.out.println("[LUMOS] " + s);
                //         for(String inst:stat.get(s)){
                //                 System.out.println(inst);
                //         }
                // }

        }


        public static void output(UUID id, String name, List<String> logs){
                File outputDir = new File(dir);
                if (!outputDir.exists()) {
                        outputDir.mkdir();
                }
                File file = new File(dir + id);
                FileWriter writer;
                // System.out.println("outputing to " + file.getName());
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
                tracer.log("begin entry func");
                System.out.println("begin: " + System.nanoTime());
        }

}
