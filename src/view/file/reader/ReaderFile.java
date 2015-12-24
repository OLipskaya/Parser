package view.file.reader;

import java.io.*;

public class ReaderFile {
    private String inputLine = null;

    public ReaderFile(){}

    public void readFile(File file){
        try {
            FileReader inputFile = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            inputLine = bufferReader.readLine();
            inputFile.close();
            bufferReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveFile(File file, String text){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getInputLine() {
        return inputLine;
    }

}
