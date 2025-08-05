package com.lumos.tracer;

import java.util.Random;

import edu.brown.cs.systems.xtrace.XTrace;

public class Test{
  public ThreadLocal<ThreadContext> tl = new ThreadLocal<>();
  public static Random r = new Random(42);
  static class Store{
    public int n1 = r.nextInt();
    public int n2 = r.nextInt();
  }
  public static void main(String args[]){
    bench1();
  }
  // public static void bench2(){
    // // XTrace;
    // LumosRegister.registerTracer("null");
    // // float t = 0.0f, s1 = 0.0f, s2 = 0.0f, s3 = 0.0f, hc = 0.0f;
    // long s,e;
    // long ts1, ts2;
    // int a,b,c;
    // int N = Integer.valueOf(System.getProperty("N"));
    // int M = Integer.valueOf(System.getProperty("M"));
    // int L = Integer.valueOf(System.getProperty("L"));
    // // char mu = '\';

    // Store[] sa1 = new Store[N];
    // Store[] sa2 = new Store[N];
   
    // for (int i = 0; i < N; i++) {
    //   sa1[i] = new Store();
    //   sa2[i] = new Store();
    // }
    
    // s = System.nanoTime();
    // for (int j = 0; j < M; j++) {
    //   for (int i = 0; i < N; i++) {
    //     Store s1 = sa1[i];
    //     Store s2 = sa2[i];
    //     a = s1.n1;
    //     s2.n1 = a;
    //     a = s1.n2;
    //     s2.n2 = a;
    //   }
    // }
    // e = System.nanoTime();
    // System.out.println("no trace: " + (e - s) / 1e3 / N / M+ "micros per iter");
    // LumosTracer.toggle(false);

    // s = System.nanoTime();

    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   Store s1 = sa1[i];
    //   LumosTracer.logNull(sa1, "");

    //   Store s2 = sa2[i];
    //   LumosTracer.logNull(sa2, "");

    //   a = s1.n1;
    //   LumosTracer.logNull(s1, "");

    //   s2.n1 = a;
    //   LumosTracer.logNull(s2, "");

    //   a = s1.n2;
    //   LumosTracer.logNull(s1, "");

    //   s2.n2 = a;
    //   LumosTracer.logNull(s2, "");
    // }
    // }
    // e = System.nanoTime();
    // System.out.println("null trace (method invocation): " + (e - s) / 1e3 / N/M + "micros per iter");

    // s = System.nanoTime();

    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   Store s1 = sa1[i];
    //   LumosTracer.logAddress(sa1, "");

    //   Store s2 = sa2[i];
    //   LumosTracer.logAddress(sa2, "");

    //   a = s1.n1;
    //   LumosTracer.logAddress(s1, "");

    //   s2.n1 = a;
    //   LumosTracer.logAddress(s2, "");

    //   a = s1.n2;
    //   LumosTracer.logAddress(s1, "");

    //   s2.n2 = a;
    //   LumosTracer.logAddress(s2, "");
    // }
    // }
    // e = System.nanoTime();
    // System.out.println("partial trace off: " + (e - s) / 1e3 / N/M + "micros per iter");

    // s = System.nanoTime();

    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   ts1 = System.nanoTime();
    //   Store s1 = sa1[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   Store s2 = sa2[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n1;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   s2.n1 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n2;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   s2.n2 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);
    // }
    // }
    // e = System.nanoTime();
    // System.out.println("full trace off: " + (e - s) / 1e3 / N/M + "microms per iter");

    // LumosTracer.toggle(true);
    // s = System.nanoTime();
    
    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   ts1 = System.nanoTime();
    //   Store s1 = sa1[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   Store s2 = sa2[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n1;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   s2.n1 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n2;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   s2.n2 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);
    // }
    // }
    // e = System.nanoTime();
    // System.out.println("full trace (null) on: " + (e - s) / 1e3 / N/M + "micros per iter");


    // LumosRegister.registerTracer("async");
    // s = System.nanoTime();
    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   ts1 = System.nanoTime();
    //   Store s1 = sa1[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   Store s2 = sa2[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n1;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   s2.n1 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);


    //   ts1 = System.nanoTime();
    //   a = s1.n2;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);

    //   ts1 = System.nanoTime();
    //   s2.n2 = a;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);
    // }
    // }
    // e = System.nanoTime();
    // System.out.println("full trace (async) on: " + (e - s) / 1e3 / N/M + "micros per iter");

    // long all = 0;
    // XTrace.startTask(true);
    // LumosRegister.registerTracer("xtrace");
    // for (int j = 0; j < M; j++) {
    // for (int i = 0; i < N; i++) {
    //   c=0;
    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   Store s1 = sa1[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa1, "", ts1, ts2);
    //   e = System.nanoTime();
    //   all+=(e-s);
    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }

    //   c=c+i;

    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   Store s2 = sa2[i];
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(sa2, "", ts1, ts2);
    //   e = System.nanoTime();
    //   all+=(e-s);

    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }

    //   c=c+j;

    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   a = s1.n1;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);
    //   e = System.nanoTime();

    //   c=c+a;
    //   all+=(e-s);
    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }

    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   s2.n1 = c;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);
    //   e = System.nanoTime();

    //   c=c+j;
    //   all+=(e-s);
    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }

    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   a = s1.n2;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s1, "", ts1, ts2);
    //   e = System.nanoTime();

    //   c=c+1;
    //   all+=(e-s);
    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }


    //   s = System.nanoTime();
    //   ts1 = System.nanoTime();
    //   s2.n2 = c;
    //   ts2 = System.nanoTime();
    //   LumosTracer.logTimedTrace(s2, "", ts1, ts2);
    //   e = System.nanoTime();

    //   all+=(e-s);
    //   try {
    //     Thread.sleep(L);
    //   } catch (InterruptedException e1) {
    //     e1.printStackTrace();
    //   }
    // }
    // }
    // System.out.println("full trace (xtrace) on: " + all / 1e3 / N/M + "micros per iter");
    
  // }



  public static void bench1(){
    float t = 0.0f, s1 = 0.0f, s2 = 0.0f, s3 = 0.0f, hc = 0.0f;
    long s,e;
    int N = 1000000;
    int M = 32768;
    String[] buffer = new String[M];
    Test test = new Test();

    for (int i = 0; i < N; i++) {
      s = System.nanoTime();
      hc = System.nanoTime();
      e = System.nanoTime();
      //System.err.println(hc);
      s1 += (e - s);
    }

    for (int i = 0; i < N; i++) {
      s = System.nanoTime();
      String st = String.valueOf(hc);
      e = System.nanoTime();
      buffer[i % M] = st;
      // System.err.println(st);
      s2 += (e - s);
    }

    for (int i = 0; i < N; i++) {
      test = new Test();
      s = System.nanoTime();
      hc += System.identityHashCode(test);
      e = System.nanoTime();
      s3 += (e - s);
    }
    System.out.println(hc);
    System.out.println("time taking = " + (s1/N) + "ns");
    System.out.println("log time = " + (s2/N) + "ns");
    System.out.println("hashcode = " + (s3/N) + "ns");
  }
}
