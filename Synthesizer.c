#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "Synthesizer.h"   // Generated

#include <fluidsynth.h>
#if defined(WIN32)
#include <windows.h>
#else
#include <stdlib.h>
#include <unistd.h>
#endif

fluid_settings_t* settings;
fluid_synth_t* synth;
fluid_audio_driver_t* adriver;

JNIEXPORT jboolean JNICALL Java_Synthesizer_open(JNIEnv* env, jobject thisObj, jstring soundfont) {
   settings = new_fluid_settings();
   synth = new_fluid_synth(settings);
   adriver = new_fluid_audio_driver(settings, synth);

   const char* soundfontPointer = (*env)->GetStringUTFChars(env, soundfont, NULL);
   int sfont_id = fluid_synth_sfload(synth, soundfontPointer, 1);
   return sfont_id == FLUID_FAILED ? 0 : 1;
}

JNIEXPORT void JNICALL Java_Synthesizer_noteOn(JNIEnv* env, jobject thisObj, jint midiNumber) {
   fluid_synth_noteon(synth, 0, midiNumber, 127);
}

JNIEXPORT void JNICALL Java_Synthesizer_noteOff(JNIEnv* env, jobject thisObj, jint midiNumber) {
   fluid_synth_noteoff(synth, 0, midiNumber);
}

JNIEXPORT void JNICALL Java_Synthesizer_close(JNIEnv* env, jobject thisObj) {
   delete_fluid_audio_driver(adriver);
   delete_fluid_synth(synth);
   delete_fluid_settings(settings);
}
