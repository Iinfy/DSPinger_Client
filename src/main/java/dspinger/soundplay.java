package dspinger;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class soundplay implements Runnable{
    private File soundFile;
    private String filePath;
    public soundplay(String filepath){
        this.filePath = filepath;
        soundFile = new File(filePath); 
    }


    public void play() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0); 
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);
            clip.stop(); 
            clip.close(); 
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
	        exc.printStackTrace();
        } catch (InterruptedException exc) {}
    }

    public static void main(String[] args) {
        soundplay sp = new soundplay("bell.wav");
        sp.play();
    }


    @Override
    public void run() {
        play();
    }



}