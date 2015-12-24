package model.entity;

import model.entity.Element;

public class Operand implements Element {
    private Priority priority;
    private String value;

    public Operand(String value, Priority priority) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setType(Type type) {}

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return null;
    }
}
