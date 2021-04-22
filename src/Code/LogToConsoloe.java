package Code;

import java.util.LinkedList;

//Implement Concrete Observer to print log statements to console
public class LogToConsoloe implements LogObserver{
    private LinkedList<String> aLog = new LinkedList<String>();

    //Ensure that we only maintain the most recent 500 log statements to avoid memory overflow
    @Override
    public void logging(Action pAction, Robot pRobot) {
        if(this.aLog.size() <= 500){
            this.aLog.add(pAction.toString() + " action performed, battery level is " + pRobot.getBatteryCharge());
        }
        else{
            this.aLog.removeFirst();
            this.aLog.add(pAction.toString() + " action performed, battery level is " + pRobot.getBatteryCharge());
        }
    }

    //Enable the client to clear all logs manually
    public void clearLog(){
        this.aLog.clear();
    }

    //Print to the console
    public void printLog(){
        for(String s : this.aLog){
            System.out.println(s);
        }
    }

}
