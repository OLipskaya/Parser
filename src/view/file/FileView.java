package view.file;

import controller.LineController;
import model.entity.Type;
import model.objects.LineElement;
import view.View;
import view.file.reader.ReaderFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileView implements View {
    private Type type;
    private LineElement lineElement = null;
    private ReaderFile fileReader;
    private LineController lineController;
    private String resultLine = null;

    public FileView() {
        fileReader = new ReaderFile();
    }

    public void openFile(File file, Type type){
        setType(type);
        fileReader.readFile(file);
        String input = fileReader.getInputLine();
        if(!input.equals(null)){
            lineElement = new LineElement(input);
            setLineElement(lineElement);
            calculate();
        }
    }

    private boolean checkLine(String str){
        String template = "[*+-/()[0-9]ABCDEF]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    public void calculate(){
        lineController = new LineController(lineElement, this.type);
        resultLine = lineController.getResultLine();
    }

    public void saveFile(File file){
        fileReader.saveFile(file, resultLine);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public LineElement getLineElement() {
        return lineElement;
    }

    @Override
    public void setLineElement(LineElement line) {
        this.lineElement = line;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    public String getResLine() {
        return resultLine;
    }

    public void setResLine(String resLine) {
        this.resultLine = resLine;
    }
}
