/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author JACOW
 */
public class svmbagi {

    /**
     * @param args the command line arguments
     */
    
    public static void randomize(double percentageInTrain, String inputFile, String outTrain, String outTest) {
        try(Stream<String> stream = Files.lines(Paths.get(inputFile));
            BufferedWriter btrain = Files.newBufferedWriter(Paths.get(outTrain), StandardCharsets.UTF_8);
            BufferedWriter btest = Files.newBufferedWriter(Paths.get(outTest), StandardCharsets.UTF_8)) {
        
            Object[] lines = stream.toArray();
            
            int numDataInTrain = (int)((percentageInTrain/100)*lines.length);
            int numDataCurrentlyInTrain = 0;
            
            for (Object line : lines) {
                if(line instanceof String && (((String)line).trim().length() > 0)) {
                    String data = (String)line;
                    
                    if(Math.random()< (0.1 + percentageInTrain/100)) {
                        if (numDataCurrentlyInTrain < numDataInTrain) {
                        btrain.write(data);
                        btrain.newLine();
                        } else {
                            btest.write(data);
                            btest.newLine();
                        }
                    } else{
                        btest.write(data);
                        btest.newLine();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        randomize(70, "occupation.out", "occupation.train", "occupation.test");
        System.out.println("dal");
    }
    
}