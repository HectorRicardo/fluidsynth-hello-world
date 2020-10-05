#include <jni.h>
#include <stdio.h>
#include "FluidSynthHelloWorld.h"

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
fluid_sfont_t* sfont;

JNIEXPORT jboolean JNICALL Java_FluidSynthHelloWorld_open(JNIEnv* env, jobject thisObj, jstring soundfont) {
  settings = new_fluid_settings();
  synth = new_fluid_synth(settings);
  adriver = new_fluid_audio_driver(settings, synth);

  const char* soundfontPointer = (*env)->GetStringUTFChars(env, soundfont, NULL);
  int sfont_id = fluid_synth_sfload(synth, soundfontPointer, 1);
  if (sfont_id == FLUID_FAILED) {
    return 0;
  }
  sfont = fluid_synth_get_sfont_by_id(synth, sfont_id);
  return 1;
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_noteOn(JNIEnv* env, jobject thisObj, jint midiNumber) {
  fluid_synth_noteon(synth, 0, midiNumber, 127);
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_noteOff(JNIEnv* env, jobject thisObj, jint midiNumber) {
  fluid_synth_noteoff(synth, 0, midiNumber);
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_controlChange(JNIEnv* env, jobject thisObj, jint controller, jint value) {
  fluid_synth_cc(synth, 0, controller, value);
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_programChange(JNIEnv* env, jobject thisObj, jint program) {
  fluid_synth_program_change(synth, 0, program);
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_printLoadedInstruments(JNIEnv* env, jobject thisObj) {
  fluid_preset_t *preset;
  fluid_sfont_iteration_start(sfont);
  while((preset = fluid_sfont_iteration_next(sfont)) != NULL) {
    printf("%s\n", fluid_preset_get_name(preset));
  }
}

JNIEXPORT void JNICALL Java_FluidSynthHelloWorld_close(JNIEnv* env, jobject thisObj) {
  delete_fluid_audio_driver(adriver);
  delete_fluid_synth(synth);
  delete_fluid_settings(settings);
}
