package model.objects;

import model.entity.Priority;

import java.util.HashMap;
import java.util.Map;

public class OperandManager {
    private Map<String,Priority> operands = new HashMap<String,Priority>();
    private Map<Priority,Integer> conformity = new HashMap<Priority,Integer>();
    {
        operands.put("(", Priority.LOW);
        operands.put(")", Priority.LOW);
        operands.put("+", Priority.MEDIUM);
        operands.put("-", Priority.MEDIUM);
        operands.put("*", Priority.HIGH);
        operands.put("/", Priority.HIGH);
    }
    {
        conformity.put(Priority.LOW, 1);
        conformity.put(Priority.MEDIUM, 2);
        conformity.put(Priority.HIGH, 3);
    }

    public OperandManager() {}

    public Priority getPriroty(String key){
        return operands.get(key);
    }

    public int getConformPriority(Priority priority){
        return conformity.get(priority);
    }

    //current <= peek -> pop
    public boolean comparePriority(Priority currPriotity, Priority peekPriority){
        if(getConformPriority(currPriotity) <= getConformPriority(peekPriority)) { return true; }
        else { return false; }
    }
}
