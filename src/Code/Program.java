package Code;

import java.util.ArrayList;

public class Program implements Element{
    private String aTitle;
    private ArrayList<Action> allActions;
    private ArrayList<LogObserver> allObservers;

    public Program(String pTitle){
        this.aTitle = pTitle;
        this.allActions = new ArrayList<Action>();
        this.allObservers = new ArrayList<LogObserver>();
    }

    //Getter for the Title
    public String getTitle(){
        return this.aTitle;
    }

    //Setter for the Title
    public void setTitle(String pTitle){
        this.aTitle = pTitle;
    }

    //Enable client to add an action in the end of the program
    public void addAction(Action pAction){
        this.allActions.add(pAction);
    }

    //Enable client to add an action in a specific position
    public void insertAction(int pIndex, Action pAction){
        this.allActions.add(pIndex, pAction);
    }

    //Remove the input action from the program
    public void removeAction(Action pAction){
        this.allActions.remove(pAction);
    }

    //Remove the action specified by the index from the program
    public void removeAction(int pIndex){
        this.allActions.remove(pIndex);
    }

    //Enable the client to add Observer(A way to log) for current program
    public void addObserver(LogObserver pObserver){
        this.allObservers.add(pObserver);
    }

    //Enable the client to remove Observer(A way to log) for current program
    public void removeObserver(LogObserver pObserver){
        this.allObservers.remove(pObserver);
    }

    //Execute the whole program
    public void executeProgram(Robot r) throws RobotDamagedException {
        for(Action action : this.allActions){
            action.execute(r);
            //if executed successfully, log to all observers
            for(LogObserver observer : this.allObservers){
                observer.logging(action, r);
            }
        }
    }

    /*
    Get the shallow copy of the Program
    Safe to have basic action since they are unmodifiable
    Safe to have RecargeBeforeAction since it's unmodifiable
    Safe to have CompositeAction since we may modify them later, same reasons as why we don't copy
    it when we add it to the Program
     */
    public ArrayList<Action> getAllActions(){
        ArrayList<Action> toReturn = new ArrayList<>();
        for(Action action : this.allActions){
            toReturn.add(action);
        }
        return toReturn;
    }

    @Override
    public Object accept(Visitor pVisitor) {
        return pVisitor.visitProgram(this);
    }
}
