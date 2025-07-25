package com.lumos.tracer.tracer;

import org.apache.log4j.Logger;

import com.lumos.tracer.LumosTracer;

public class FileTracer implements Tracer{
        public static Logger logger;
        static {
                logger = Logger.getLogger(LumosTracer.class);
        }
        @Override
        public void log(String msg) {
                logger.info(Thread.currentThread().getId() + "," + msg);
        }
        @Override
        public void log(long msg) {
                // TODO Auto-generated method stub
                //
                throw new UnsupportedOperationException("Unimplemented method 'log'");
        }

}
