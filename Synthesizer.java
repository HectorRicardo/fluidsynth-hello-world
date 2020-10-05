public class Synthesizer {
   static {
      System.loadLibrary("synthesizer");
   }

   private static final Synthesizer instance = new Synthesizer(); 

   private Synthesizer() {}
 
   native boolean open(String soundfontFile);
   native void noteOn(int midiNumber);
   native void noteOff(int midiNumber);
   native void close();
 
   public static void main(String[] args) throws InterruptedException {
      Synthesizer synthesizer = Synthesizer.instance;
      boolean success = synthesizer.open("example.dls");
      if (!success) return;

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
