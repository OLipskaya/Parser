package view.view;

import controller.LineController;
import model.entity.Type;
import model.objects.LineElement;
import view.View;

public class ConsoleView implements View {
    private LineElement lineElement;
    private LineController lineController;
    private String resultLine;
    private boolean errorType = false;

    public ConsoleView(int numType, String inputLine) {
        Type type = getElementType(numType);
        lineElement = new LineElement(inputLine);
        lineController = new LineController(lineElement,type);
        resultLine = lineController.getResultLine();
    }

    private Type getElementType(int numNumber){
        Type type;
        switch(numNumber){
            case 1:
                type = Type.BINARY;
                break;
            case 2:
                type = Type.DECIMAL;
                break;
            case 3:
                type = Type.HEXDECIMAL;
                break;
            default:
                type = Type.DECIMAL;
                break;
        }
        return type;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public LineElement getLineElement() {
        return null;
    }

    @Override
    public void setLineElement(LineElement line) {}

    @Override
    public void setType(Type type) {}

    public String getResultLine() {
        return resultLine;
    }

    public void setResultLine(String resultLine) {
        this.resultLine = resultLine;
    }

    public boolean isErrorType() {
        return errorType;
    }
}
