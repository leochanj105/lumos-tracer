#include "com_lumos_tracer_HindsightJNI.h"
#include "stdio.h"
#include "hindsight.h"
#include "assert.h"

JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightInit
  (JNIEnv * env, jclass cls, jstring proc, jstring config){
    printf("start initing...\n");
    const char *procString = (*env)->GetStringUTFChars(env, proc, 0);
    const char *configString = (*env)->GetStringUTFChars(env, config, 0);
    HindsightConfig c = hindsight_load_config_file(configString);

    hindsight_init_with_config(procString,c);
    printf("finished initting\n");
  }

JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightTracepoint
  (JNIEnv * env, jclass cls, jbyteArray payload, jint size){
    jboolean isCopy;
    char * pCData = (char*)(*env)->GetByteArrayElements(env, payload, &isCopy);
    hindsight_tracepoint(pCData, size);
    if(isCopy)
    {
      (*env)->ReleaseByteArrayElements(env, payload, pCData, JNI_ABORT);
    }
  }

JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightBegin
  (JNIEnv * env, jclass cls, jlong traceId){
    hindsight_begin(traceId);
    /* jclass class = (*env)->FindClass(env,"com/lumos/tracer/HindsightJNI$HSTrace"); */
    
    /* if (NULL == class){ */
    /*     printf("class"); */
    /*     assert(0); */
    /* } */
    /* jmethodID mid = (*env)->GetMethodID(env,class, "<init>", "(Lcom/lumos/tracer/HindsightJNI;Ljava/nio/ByteBuffer;)V"); */

    /* if (NULL == mid){ */
    /*    printf("method\n"); */
    /*    assert(0); */
    /* } */

    /* /1* printf("%p\n",hindsight_tls.buffer.base); *1/ */
    /* return (*env)->NewObject(env, class, mid, */ 
    /*     class, */
    /*     (*env)->NewDirectByteBuffer(env,hindsight_tls.buffer.base, */ 
    /*       hindsight_tls.buffer.remaining + hindsight_tls.buffer.ptr - hindsight_tls.buffer.base)); */
  }

JNIEXPORT jobject JNICALL Java_com_lumos_tracer_HindsightJNI_switchBufferNative
  (JNIEnv *env, jclass cls){
    switchBuffer(&hindsight_tls, mgr);
    /* printf("new buffer: %p\n",hindsight_tls.buffer.base); */
    return (*env)->NewDirectByteBuffer(env,hindsight_tls.buffer.base,
      hindsight_tls.buffer.remaining + hindsight_tls.buffer.ptr - hindsight_tls.buffer.base);
  }

/* JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_returnBufferNative */
/* JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightEnd */
/*   (JNIEnv *env, jclass cls){ */
/*     bufmanager_return(mgr, hindsight_tls.header.trace_id, &hindsight_tls.buffer); */
/*     hindsight_tls.active = false; */
/*   } */
/* JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightBegin */
/*   (JNIEnv * env, jclass cls, jlong traceId){ */
/*     hindsight_begin(traceId); */
/*   } */

JNIEXPORT void JNICALL Java_com_lumos_tracer_HindsightJNI_hindsightEnd
  (JNIEnv *env, jclass cls){
    hindsight_end();
  }
