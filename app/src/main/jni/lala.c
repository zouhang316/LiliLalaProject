
#include <string.h>
#include <jni.h>
#include <android/log.h>
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, "ssx", __VA_ARGS__)
jobjectArray
Java_com_android_lala_config_LalaConfig_stringsFromJNI( JNIEnv* env,
                                                  jobject thiz )
{
	  jstring str;
	  jsize len = 1;
	  jobjectArray args = 0;
	  char* sa[] = {  "http://lelelala.net",
					 };
	  int i=0;
	  args = (*env)->NewObjectArray(env,len,(*env)->FindClass(env,"java/lang/String"),0);
	  for( i=0; i < len; i++ )
	  {
	  str = (*env)->NewStringUTF(env,sa[i] );
	  (*env)->SetObjectArrayElement(env,args, i, str);
	  }
	  return args;
}



