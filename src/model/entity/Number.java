package model.entity;

import model.entity.*;

public class Number implements Element {
    private String value;
    private Type type;

    public Number(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }
    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setPriority(Priority priority) {}

    @Override
    public Priority getPriority() {
        return null;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
