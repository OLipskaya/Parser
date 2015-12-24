package model;

import model.entity.*;
import model.entity.Number;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private List<Element> outputList = null;
    private Stack<Element> stack = null;
    private String resultLine = null;

    private static String NUMBER_NAME = model.entity.Number.class.getName();
    private static String OPERAND_NAME = model.entity.Operand.class.getName();

    public Calculator(List<Element> outputList) {
        if((outputList.size()!=0)&&(!outputList.equals(null))){
            this.outputList = outputList;
            stack = new Stack<Element>();
            initCalculation();
        }
    }

    private boolean ifOperand(String str){
        String template = "[*+-/()]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private boolean ifNumber(String str){
        String template = "[0-9]";
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(str);
        if (m.matches()) { return true; }
        else { return false; }
    }

    private void initCalculation(){
        for(Element e: outputList){
            String name = e.getClass().getName();
            if(name.equals(NUMBER_NAME)){
               //if(ifNumber(e.getValue())
                checkNumber(e);
            }
            if(name.equals(OPERAND_NAME)){
                //if(ifOperand(e.getValue())
                checkOperand(e);
            }
        }
        if(!stack.isEmpty()){
            resultLine = stack.peek().getValue();
        }
    }

    private void checkNumber(Element e){
        Type tp = e.getType();
        String value = e.getValue();
        switch (tp){
            case BINARY:
                int num = Integer.parseInt(value, 2);
                e.setValue(Integer.toString(num));
                break;
            case DECIMAL:
                int dNum = Integer.parseInt(value, 10);
                e.setValue(Integer.toString(dNum));
                break;
            case HEXDECIMAL:
                int hNum = Integer.parseInt(value, 16);
                e.setValue(Integer.toString(hNum));
                break;
        }
        stack.push(e);
    }

    private void checkOperand(Element e){
        String operation = e.getValue();
        Element el1 = stack.pop();
        Element el2 = null;
        if(!stack.isEmpty()){
            el2 = stack.pop();
            int b = Integer.parseInt(el1.getValue());
            int a = Integer.parseInt(el2.getValue());
            int res = 0;

            switch(operation){
                case "+":
                    res = a + b;
                    break;
                case "-":
                    res = a - b;
                    break;
                case "*":
                    res = a * b;
                    break;
                case "/":
                    res = a / b;
                    break;
            }

            Number number = new Number(Integer.toString(res), e.getType());
            stack.push(number);
        }
    }

    public String getResultLine() {
        return resultLine;
    }
}
