/*
 * Course: CS-1021-021
 * Winter 2018
 * Lab 1 - Wav Files
 * Name: Stuart Harley
 * Created: 11/29/18
 */

package harleys;

import edu.msoe.taylor.audio.WavFile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gives the user several options dealing with wav files. They can reverse a wav file,
 * create a new one at a specific frequency, or end the program.
 */
public class Lab1 {

    /**
     * Scanner object to be used throughout the class
     */
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String input = "";
        while (!input.equals("0")) {
            do {
                System.out.print("Enter 0 (end), 1 (reverse), or 2 (new at specified freq): ");
                input = in.next();
            } while (!(input.equals("0") || input.equals("1") || input.equals("2")));
            switch (input) {
                case "0":
                    break;
                case "1":
                    reverseSample();
                    break;
                case "2":
                    createWav();
                    break;
            }
        }
    }

    /**
     * Reverses the sample of a desired wav file and creates a new wav file
     * with this reversed sample adding Rev to the name of the new file
     */
    private static void reverseSample(){
        System.out.print("\nEnter a filename without the .wav extension: ");
        String fileName = in.next();
        WavFile original = new WavFile(fileName + ".wav");
        ArrayList<Double> originalSample = original.getSamples();
        ArrayList<Double> revSample = new ArrayList<>();
        for (int i = originalSample.size()-1; i>=0; i--) {
            revSample.add(originalSample.get(i));
        }
        WavFile reverse = new WavFile(fileName + "Rev.wav", original.getNumChannels(),
                original.getNumFrames(), original.getValidBits(), original.getSampleRate());
        reverse.setSamples(revSample);
        reverse.close();
    }

    /**
     * Creates a new wav file with a desired name and frequency that lasts 1 second
     */
    private static void createWav(){
        System.out.print("\nEnter a filename without the .wav extension: ");
        String filename = in.next();
        System.out.print("\nEnter a frequency: ");
        int frequency = in.nextInt();
        WavFile newSound = new WavFile(filename + ".wav", 1, 25000, 16, 25000);
        ArrayList<Double> sample = new ArrayList<>();
        for(int i = 0; i<newSound.getSampleRate(); i++){
            sample.add(Math.sin(2*Math.PI*i*(frequency*1.0/newSound.getSampleRate())));
        }
        newSound.setSamples(sample);
        newSound.close();
    }
}