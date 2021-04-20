package Code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogToFile implements LogObserver{
    private File aLogFile;

    //Let client explore where they want to store the log information
    public LogToFile(String pPath){
        try{
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
        catch(IOException e){
            System.out.println("Error: Fail To Create The Log File.");
            e.printStackTrace();
        }
    }

    @Override
    public void logging(Action pAction, Robot pRobot){
        try{
            FileWriter aWriter = new FileWriter(this.aLogFile.getName());
            aWriter.write(pAction.toString() + " action performed, battery level is " + pRobot.getBatteryCharge());
            aWriter.close();
        }
        catch (IOException e){
            System.out.println("Error: Fail Log Information Of Action " + pAction.toString() +
                    " With Robot Battery Level Of " + pRobot.getBatteryCharge());
            e.printStackTrace();
        }
    }
}
