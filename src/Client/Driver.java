package Client;

import Code.*;

public class Driver {
    public static void main(String[] args) throws RobotDamagedException {
        //Create a robot
        WallE myRobot = new WallE();
        //Create several actions
        MoveForward mf1 = MoveForward.getInstance(10);
        Turn turnRight = Turn.getInstance(Direction.Right);
        MoveForward mf2 = MoveForward.getInstance(20);
        Grab grab = Grab.getInstance();
        Compact compact = Compact.getInstance();
        Release release = Release.getInstance();
        EmptyCompactor empty = EmptyCompactor.getInstance();
        RechargeBeforeAction recharge = new RechargeBeforeAction(mf1);
        CompositeAction com = new CompositeAction("Move Foward 20 and Turn Left");
        Turn turnLeft = Turn.getInstance(Direction.Left);
        com.addAction(mf2);
        com.addAction(turnLeft);
        //Create a Program to assemble all actions
        Program myProgram = new Program("Go Forward then Turn Right");
        myProgram.addAction(mf1);
        myProgram.addAction(turnRight);
        myProgram.addAction(mf2);
        myProgram.addAction(grab);
        myProgram.addAction(compact);
        myProgram.addAction(turnRight);
        myProgram.addAction(mf1);
        myProgram.addAction(grab);
        myProgram.addAction(release);
        //myProgram.addAction(compact); // Exception
        myProgram.addAction(empty);
        myProgram.addAction(recharge);
        myProgram.addAction(com);
        //Two ways to log the execution history
        LogToConsoloe consoleObserver = new LogToConsoloe();
        //Use the directory you want to store the log file
        LogToFile fileObserver = new LogToFile("/Users/stevenyuan/Documents/McGill/U1/2021winter/" +
                "Comp303/Assignment/Comp303_Assignment6/src/Client");
        myProgram.addObserver(consoleObserver);
        myProgram.addObserver(fileObserver);
        //Execute the program
        myProgram.executeProgram(myRobot);
        //Compute based on the program
        GetDistance distanceVisitor = new GetDistance();
        GetNumOfCompacted numVisitor = new GetNumOfCompacted();
        Object distance = myProgram.accept(distanceVisitor);
        Object num = myProgram.accept(numVisitor);
        System.out.println("Total Distance Moved: " + distance);
        System.out.println("Total Number of Compacted Items: " + num);
        //Print out the Log
        consoleObserver.printLog();
    }
}
