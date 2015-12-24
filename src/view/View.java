package view;

import model.entity.Type;
import model.objects.LineElement;

public interface View {
    public Type getType();
    public LineElement getLineElement();
    public void setLineElement(LineElement line);
    public void setType(Type type);
}
