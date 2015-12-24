package model.objects;

import model.entity.*;
import model.entity.Number;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SequenceElement {
    private LineElement lineElement;
    private List<Element> elementList;
    private OperandManager operandManager;
    private Type type;

    public SequenceElement(LineElement lineElement,Type type) {
        this.type = type;
        this.lineElement = lineElement;
        this.elementList = new ArrayList<Element>();
        operandManager = new OperandManager();
        tokenizerLine();
    }

    public void setTypeElement(Type type){
        this.type = type;
    }

    private void checkType(String value){
        Pattern pb = Pattern.compile("[0-1]");
        Matcher mb = pb.matcher(value);
        if (mb.matches()) { type = Type.BINARY; }

        Pattern pd = Pattern.compile("[0-9]");
        Matcher md = pd.matcher(value);
        if (md.matches()) { type = Type.DECIMAL; }

        Pattern ph = Pattern.compile("[0-9ABCDEF]");
        Matcher mh = ph.matcher(value);
        if (mh.matches()) { type = Type.HEXDECIMAL; }
    }

    private Number createNumber(String value){
        checkType(value);
        return new Number(value,type);
    }

    private Operand createOperand(String value){
        return new Operand(value,operandManager.getPriroty(value));
    }

    private boolean checkOperand(String str){
        String template = "[*+-/()]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private boolean checkNumber(String str){
        String template = "[0-9]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private void tokenizerLine(){
        String inputLine = lineElement.getInputLine();
        StringBuilder builder = new StringBuilder(inputLine);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while(i < builder.length()){
            String sm = builder.substring(i, i+1);
            if(checkOperand(sm)){
                if(sb.length()!=0){
                    elementList.add(createNumber(sb.toString()));
                    sb.setLength(0);
                }
                elementList.add(createOperand(sm));
            } else {
                sb.append(sm);
            }
            i++;
        }
        if(sb.length()!=0){
            elementList.add(createNumber(sb.toString()));
        }
    }

    public LineElement getLineElement() {
        return lineElement;
    }

    public void setLineElement(LineElement lineElement) {
        this.lineElement = lineElement;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }
}
