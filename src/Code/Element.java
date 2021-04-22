package Code;

//To implement the visitor design pattern, abstract out the element interface
public interface Element {
    public Object accept(Visitor pVisitor);
}
