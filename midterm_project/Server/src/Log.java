import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Log {   
    public static String m_FileName = "log";     
    private static FileWriter objfile = null;  
    public static void TraceLog(String log) {
        String stPath = "";
        String stFileName = "";     
        String m_PathName = "./"; 
        
        stPath     = m_PathName;
        stFileName = m_FileName;
        SimpleDateFormat formatter1 = new SimpleDateFormat ("yyyy.MM.dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat ("HH:mm:ss");
       
        String stDate = formatter1.format(new Date());
        String stTime = formatter2.format(new Date());
        StringBuffer bufLogPath  = new StringBuffer();      
	    bufLogPath.append(stPath + stFileName + "_" + stDate + ".log");
        StringBuffer bufLogMsg = new StringBuffer();
        bufLogMsg.append("[" + stDate + "." + stTime + "]\r\n" + log);                    
        try { 
        	objfile = new FileWriter(bufLogPath.toString(), true);
            objfile.write(bufLogMsg.toString());
            objfile.write("\r\n");
        } catch(IOException e){}
        finally
        {
            try{ objfile.close(); }
            catch(Exception e1){}
        }
    }
}