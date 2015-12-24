package model.objects;

public class LineElement {
    private String inputLine = null;

    public LineElement(String inputLine) {
        this.inputLine = inputLine;
        inputLine = inputLine.replace(" ", "");
    }

    public String getInputLine() {
        if(!inputLine.equals(null)){
            return inputLine;
        }
        return "";
    }

    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }
}
