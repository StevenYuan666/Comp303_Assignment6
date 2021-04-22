package Code;

import java.util.ArrayList;

public class CompositeAction implements Action{
    private ArrayList<BasicAction> allAction;
    private String aTitle;

    public CompositeAction(String pTitle){
        this.aTitle = pTitle;
        this.allAction = new ArrayList<BasicAction>();
    }

    //Shallow copy constructor
    public CompositeAction(CompositeAction pCompositeAction){
        this.aTitle = pCompositeAction.aTitle;
        this.allAction = new ArrayList<BasicAction>(pCompositeAction.allAction);
    }

    //Getter for Title
    public String getTitle(){
        return this.aTitle;
    }

    //Setter for Title
    public void setTitle(String pTitle){
        this.aTitle = pTitle;
    }

    //Enable the client to add the Basic action to end of the composite action
    public void addAction(BasicAction pAction){
        this.allAction.add(pAction);
    }

    //Enable the client to insert a Basic action to a specific position
    public void insertAction(int pIndex, BasicAction pAction){
        this.allAction.add(pIndex, pAction);
    }

    //Remove the input Basic action
    public void removeAction(BasicAction pAction){
        this.allAction.remove(pAction);
    }

    //Remove the Basic action in the specified position
    public void removeAction(int pIndex){
        this.allAction.remove(pIndex);
    }

    /*
    Since all Basic Action are unmodifiable, shallow copy is safe enough
    all Basic Actions are either singleton or flyweight
     */
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
        String allAction = "";
        for(BasicAction action : this.allAction){
            allAction += action.toString() + " ";
        }
        return "Composite Action: " + this.aTitle + " including { " + allAction + "}";
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitCompositeAction(this);
    }
}
