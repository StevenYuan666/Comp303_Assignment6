package Code;

import java.io.IOException;
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

    public String getTitle(){
        return this.aTitle;
    }

    public void setTitle(String pTitle){
        this.aTitle = pTitle;
    }

    public void addAction(Action pAction){
        this.allActions.add(pAction);
    }

    public void insertAction(int pIndex, Action pAction){
        this.allActions.add(pIndex, pAction);
    }

    public void removeAction(Action pAction){
        this.allActions.remove(pAction);
    }

    public void removeAction(int pIndex){
        this.allActions.remove(pIndex);
    }

    public void addObserver(LogObserver pObserver){
        this.allObservers.add(pObserver);
    }

    public void removeObserver(LogObserver pObserver){
        this.allObservers.remove(pObserver);
    }

    public void executeProgram(Robot r) throws RobotDamagedException {
        for(Action action : this.allActions){
            action.execute(r);
            for(LogObserver observer : this.allObservers){
                observer.logging(action, r);
            }
        }
    }

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
