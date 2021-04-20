package Code;

import java.util.ArrayList;

public class CompositeAction implements Action{
    private ArrayList<BasicAction> allAction;
    private String aTitle;

    public CompositeAction(String pTitle){
        this.aTitle = pTitle;
        this.allAction = new ArrayList<BasicAction>();
    }

    public String getTitle(){
        return this.aTitle;
    }

    public void setTitle(String pTitle){
        this.aTitle = pTitle;
    }

    public void addAction(BasicAction pAction){
        this.allAction.add(pAction);
    }

    public void insertAction(int pIndex, BasicAction pAction){
        this.allAction.add(pIndex, pAction);
    }

    public void removeAction(BasicAction pAction){
        this.allAction.remove(pAction);
    }

    public void removeAction(int pIndex){
        this.allAction.remove(pIndex);
    }

    //Since all Basic Action are unmodifiable, shallow copy is safe enough
    public ArrayList<BasicAction> getAllActions(){
        ArrayList<BasicAction> toReturn = new ArrayList<>();
        for(BasicAction action : this.allAction){
            toReturn.add(action);
        }
        return toReturn;
    }

    @Override
    public void execute(Robot r) throws RobotDamagedException {
        for(BasicAction action : this.allAction){
            action.execute(r);
        }
    }

    @Override
    public String toString(){
        return "Composite Action: " + this.aTitle;
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitCompositeAction(this);
    }
}
