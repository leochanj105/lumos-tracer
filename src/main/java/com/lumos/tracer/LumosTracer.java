package com.lumos.tracer;

import org.apache.log4j.Logger;

public class LumosTracer {
        public static ThreadLocal<Flag> rrOn = ThreadLocal.withInitial(() -> new Flag());
        public static Logger logger;
        static {
                // String propertiesFile =
                // "/home/jingyuan/lumos/lumos-tracer/src/main/resources/log4j.properties";
                // Configurator source = new Configurator(new FileInputStream(propertiesFile),
                // new File(propertiesFile));
                // Configurator.initialize(null,propertiesFile);
                // logger = LogManager.getLogger(LumosTracer.class);

                logger = Logger.getLogger(LumosTracer.class);
        }

        public static void logSysOut(Object content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(int content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(byte content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(float content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(double content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(char content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(char[] content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + new String(content));
                }
        }

        public static void logSysOut(boolean content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logSysOut(long content, String tag) {
                if (isRecordingOn()) {
                        System.out.println(tag + content);
                }
        }

        public static void logIndexedTimedTrace(Object content, int index, String tag, long start, long end) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(start + "," + end + "," + +index + "," + tag + "," +  System.identityHashCode(content));
                        toggle(true);
                }
        }

        public static void logTimedTrace(Object content, String tag, long start, long end) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(start + "," + end + "," + tag +  "," + System.identityHashCode(content));
                        toggle(true);
                }
        }

        public static void logEmptyTimedTrace(String tag, long start, long end) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(start + "," + end + "," + tag);
                        toggle(true);
                }
        }
        public static void logClass(Object content, String tag){
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content.getClass());
                        toggle(true);
                }
        }

        public static void logAddress(Object content, String tag){
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + System.identityHashCode(content));
                        toggle(true);
                }
        }

        public static void logTrace(Object content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(int content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(byte content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(float content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(double content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(char content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(char[] content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + System.identityHashCode(content));
                        toggle(true);
                }
        }

        public static void logTrace(boolean content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void logTrace(long content, String tag) {
                if (isRecordingOn()) {
                        toggle(false);
                        log(tag +  "," + content);
                        toggle(true);
                }
        }

        public static void log(String s) {
                logger.info(Thread.currentThread().getId() + "," + s);
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
                return rrOn.get().on;
        }

        // public static void logSysOut()
        public static void toggle(boolean on) {
                rrOn.get().on = on;
        }
}
