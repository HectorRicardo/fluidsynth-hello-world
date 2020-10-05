Command to make compile:

javac -h . FluidSynthHelloWorld.java

gcc -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o FluidSynthHelloWorld.dll FluidSynthHelloWorld.c -lfluidsynth

To run:

java FluidSynthHelloWorld