public class FluidSynthHelloWorld {
  static {
    System.loadLibrary("FluidSynthHelloWorld");
  }

  private static final FluidSynthHelloWorld instance = new FluidSynthHelloWorld(); 

  private FluidSynthHelloWorld() {}

  native boolean open(String soundfontFile);
  native void noteOn(int midiNumber);
  native void noteOff(int midiNumber);
  native void controlChange(int controller, int value);
  native void printLoadedInstruments();
  native void close();

  public static void main(String[] args) throws InterruptedException {
    FluidSynthHelloWorld synthesizer = FluidSynthHelloWorld.instance;
    boolean success = synthesizer.open("example.sf2");
    if (!success) return;

    synthesizer.printLoadedInstruments();

    synthesizer.controlChange(65, 127);
    synthesizer.controlChange(5, 1);

    synthesizer.noteOn(60);
    synthesizer.noteOn(64);
    synthesizer.noteOn(67);
    Thread.sleep(500);
    synthesizer.noteOff(60);
    synthesizer.noteOff(64);
    synthesizer.noteOff(67);

    synthesizer.noteOn(62);
    synthesizer.noteOn(65);
    synthesizer.noteOn(69);
    Thread.sleep(500);
    synthesizer.noteOff(62);
    synthesizer.noteOff(65);
    synthesizer.noteOff(69);

    synthesizer.noteOn(64);
    synthesizer.noteOn(67);
    synthesizer.noteOn(71);
    Thread.sleep(500);
    synthesizer.noteOff(64);
    synthesizer.noteOff(67);
    synthesizer.noteOff(71);

    synthesizer.close();
  }
}
