package Code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Implement a concrete Observer so that client can write log statements to the specified file
public class LogToFile implements LogObserver{
    private File aLogFile;
    private FileWriter aWriter;

    //Let client explore where they want to store the log information
    public LogToFile(String pPath){
        try{
            //Create a file in the given path
            this.aLogFile = new File(pPath + "/log.txt");
            if(this.aLogFile.createNewFile()){
                System.out.println("Log File Created: " + this.aLogFile.getName());
            }
            else{
                System.out.println("Log File Already Exists.");
            }
            this.aLogFile.setWritable(true);
            this.aLogFile.setReadable(true);
        }
        catch(IOException e) {
            System.out.println("Error: Fail To Create The Log File.");
            e.printStackTrace();
        }
        try{
            this.aWriter = new FileWriter(this.aLogFile);
        }
        catch(IOException e){
            System.out.println("Error: Fail To Create The Log File Writer.");
            e.printStackTrace();
        }
    }

    @Override
    public void logging(Action pAction, Robot pRobot){
        //Write the statements to the file
        try{
            aWriter.write(pAction.toString() + " action performed, battery level is "
                    + pRobot.getBatteryCharge() + "\n");
            aWriter.flush();
        }
        catch (IOException e){
            System.out.println("Error: Fail To Log Information Of Action " + pAction.toString() +
                    " With Robot Battery Level Of " + pRobot.getBatteryCharge());
            e.printStackTrace();
        }
    }
}