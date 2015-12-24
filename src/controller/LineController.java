package controller;

import model.Calculator;
import model.Handler;
import model.entity.Element;
import model.entity.Type;
import model.objects.LineElement;
import model.objects.SequenceElement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineController {
    private LineElement lineElement;
    private SequenceElement sequenceElement;
    private Handler handler;
    private Calculator calculator;
    private String resultLine = null;
    private Type type;
    private boolean errorType = false;

    public LineController(LineElement lineElement, Type type) {
        this.lineElement = lineElement;
        this.type = type;
        initParam(lineElement);
    }

    private boolean checkType(Type type, String line){
        switch(type){
            case BINARY:
                if(ifBinaryNumber(line)) { return true; }
                break;
            case DECIMAL:
                if(ifDecimalNumber(line)){ return true; }
                break;
            case HEXDECIMAL:
                if(ifHexDecimalNumber(line)){ return true; }
                break;
        }
        return false;
    }

    private boolean ifBinaryNumber(String str){
        String template = "[*+-/()0-1]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private boolean ifDecimalNumber(String str){
        String template = "[*+-/()0-9]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private boolean ifHexDecimalNumber(String str){
        String template = "[*+-/()0-9ABCDEF]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }


    private void initParam(LineElement lineElement){
        if(!lineElement.equals(null)){
            sequenceElement = new SequenceElement(lineElement,type);

            List<Element> listToken = sequenceElement.getElementList();
            if((!listToken.equals(null)&&(listToken.size()!=0))){
                handler = new Handler(listToken);

                List<Element> calList = handler.getOutputList();
                if((!calList.equals(null)&&(calList.size()!=0))) {
                    calculator = new Calculator(calList);
                    resultLine = calculator.getResultLine();
                }
            }
        }
    }

    public LineElement getLineElement() {
        return lineElement;
    }

    public void setLineElement(LineElement lineElement) {
        this.lineElement = lineElement;
    }

    public String getResultLine() {
        return resultLine;
    }

    public boolean isErrorType() {
        return errorType;
    }
}