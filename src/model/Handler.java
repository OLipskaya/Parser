package model;

import model.entity.*;
import model.entity.Element;
import model.entity.Number;
import model.objects.OperandManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Handler {
    private Stack<Element> stack;
    private List<Element> inputList;
    private List<Element> outputList;
    private OperandManager opManager;

    private static String NUMBER_NAME = Number.class.getName();
    private static String OPERAND_NAME = Operand.class.getName();

    public Handler(){}

    public Handler(List<Element> elementList){
        inputList = elementList;
        stack = new Stack<Element>();
        opManager = new OperandManager();
        outputList = new ArrayList<Element>();
        initAlgorithm();
    }

    private void initAlgorithm(){
        for(Element e: inputList){
            String name = e.getClass().getName();
            if(name.equals(NUMBER_NAME)){
                outputList.add(e);
            }
            if(name.equals(OPERAND_NAME)){
                checkElement(e);
            }
        }
        popAll();
    }

    /* Stack Manager */
    private void deleteDoValue(String value){
        while(!stack.isEmpty()){
            String st = stack.peek().getValue();
            if(st.equals(value)){
                stack.pop();
                break;
            }
            if(!st.equals(value)){
                outputList.add(stack.peek());
                stack.pop();
            }
        }
    }

    private void popAll(){
        while(!stack.isEmpty()){
            Element peekEl = stack.peek();
            if(!peekEl.getValue().equals("(")){
                outputList.add(peekEl);
                stack.pop();
            }
        }
    }

    private void popDo(Element currEl){
        while(!stack.isEmpty()){
            Element peekEl = stack.peek();
            Priority peekPr = peekEl.getPriority();
            Priority currPr = currEl.getPriority();
            if(opManager.comparePriority(currPr, peekPr)){
                if(!peekEl.getValue().equals("(")){
                    outputList.add(peekEl);
                    stack.pop();
                } else { break; }
            } else break;
        }
    }

    private void pushEl(Element e){
        stack.push(e);
    }

    private void checkElement(Element e){
        String value = e.getValue();
        switch(value){
            case "(":
                pushEl(e);
                break;
            case ")":
                deleteDoValue("(");
                break;
            default:
                popDo(e);
                pushEl(e);
                break;
        }
    }

    public List<Element> getOutputList() {
        return outputList;
    }

}
