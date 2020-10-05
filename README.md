Command to make compile:

javac -h . Synthesizer.java

gcc -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o synthesizer.dll Synthesizer.c -lfluidsynth

To run:

java Synthesizer