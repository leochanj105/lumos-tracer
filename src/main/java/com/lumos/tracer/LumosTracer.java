package com.lumos.tracer;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;


// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class LumosTracer{
        public static ThreadLocal<Boolean> rrOn = new ThreadLocal<>();
        public static Logger logger;
        static{
                // String propertiesFile =  "/home/jingyuan/lumos/lumos-tracer/src/main/resources/log4j.properties";
                // Configurator source = new Configurator(new FileInputStream(propertiesFile), new File(propertiesFile));
                // Configurator.initialize(null,propertiesFile);
                // logger = LogManager.getLogger(LumosTracer.class);

                logger = Logger.getLogger(LumosTracer.class);

                // System.out.println(logger.getAppender("file"));
        }
        public static void logSysOut(Object content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(int content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(byte content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(float content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(double content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(char content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(char[] content, String tag){
                if(checkValid()){
                        System.out.println(tag + new String(content));
                }
        }
        public static void logSysOut(boolean content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }
        public static void logSysOut(long content, String tag){
                if(checkValid()){
                        System.out.println(tag+content);
                }
        }


        public static void logTrace(Object content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(int content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(byte content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(float content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(double content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(char content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(char[] content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag + new String(content));
                        toggle(true);
                }
        }
        public static void logTrace(boolean content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static void logTrace(long content, String tag){
                if(checkValid()){
                        toggle(false);
                        logger.info(tag+content);
                        toggle(true);
                }
        }
        public static boolean checkValid() {
                // StackTraceElement[] stack = (new Throwable()).getStackTrace();
                // return false;
                // 0 = checkValid, 1 = most recent logSysOut
                //for (int i = 2; i < stack.length; i++) {
                //        StackTraceElement e = stack[i];
                //        String mname = e.getMethodName();
                //        if (mname.equals("logSysOut") || 
                //                mname.equals("logTrace")
                //                       || mname.equals("println")
                //                        //|| (mname.equals("get") && e.getClassName().equals("ThreadLocal")) 
                //                        // || mname.equals("<clinit>")
                //                        ) {
                //                return false;
                //        }

                //}
                if (rrOn.get() == null) {
                        return false;
                }
                return true;
        }
        // public static void logSysOut()
        public static void toggle(boolean on){
                rrOn.set(on?Boolean.TRUE: null);
        }
}
