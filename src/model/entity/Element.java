package model.entity;

public interface Element {
    public void setValue(String value);
    public void setType(Type type);
    public void setPriority(Priority priority);
    public Priority getPriority();
    public String getValue();
    public Type getType();
}
