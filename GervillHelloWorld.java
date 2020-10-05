import java.io.File;
import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class GervillHelloWorld {
  public static void main(String[] args) throws MidiUnavailableException, InterruptedException, InvalidMidiDataException, IOException {
    Soundbank soundbank = MidiSystem.getSoundbank(new File("example.dls"));
    Instrument instrument = soundbank.getInstrument(new Patch(0, 0));

    Synthesizer synthesizer = MidiSystem.getSynthesizer();
    synthesizer.open();

    synthesizer.unloadAllInstruments(synthesizer.getDefaultSoundbank());
    synthesizer.loadInstrument(instrument);

    MidiChannel channel = synthesizer.getChannels()[0];

    channel.programChange(0, 0);

    channel.controlChange(65, 127);
    channel.controlChange(5, 64);

    channel.noteOn(60, 64);
    channel.noteOn(64, 64);
    channel.noteOn(67, 64);
    Thread.sleep(500);
    channel.noteOff(60);
    channel.noteOff(64);
    channel.noteOff(67);

    channel.noteOn(62, 64);
    channel.noteOn(65, 64);
    channel.noteOn(69, 64);
    Thread.sleep(500);
    channel.noteOff(62);
    channel.noteOff(65);
    channel.noteOff(69);

    channel.noteOn(64, 64);
    channel.noteOn(67, 64);
    channel.noteOn(71, 64);
    Thread.sleep(500);
    channel.noteOff(64);
    channel.noteOff(67);
    channel.noteOff(71);

    synthesizer.close();
  }
}
