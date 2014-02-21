package syslogreader.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import syslogreader.domain.SyslogData;

public class SyslogDataFileWriter {

    private String homeDirPath = "C://syslogReader";
    private final String SEP = System.getProperty("file.separator");
    private DateFormat df = new SimpleDateFormat("dd_MMM_yyyy");
    private String statHtmlFName = "Statistics Viewer.html";
    
    private Constants constF = new Constants();
    private String outputTxtFileName = (df.format(Calendar.getInstance().getTime())) + ".txt";
    

    public void saveLogDataToLocalFile(SyslogData syslogData) {

        File file = new File(homeDirPath);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("Dir: " + file.getAbsolutePath());
            file.mkdir();
        }
        file = new File(homeDirPath + SEP + this.outputTxtFileName);

        try {
            //System.out.println("Writing to file: "+this.outputTxtFileName+" ["+syslogData.outputIntoFile()+"]");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, file.exists())));
            out.println(syslogData.outputIntoFile() + ",");
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SyslogDataFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SyslogDataFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File buildHTML_JSFiles() throws IOException {
        
        this.constF.setTxtfile(this.outputTxtFileName);
        
        File htmlFile = new File(homeDirPath + SEP + this.statHtmlFName);
        File jsTableStatFile = new File(homeDirPath + SEP + "tableStat.js");
        File jsUtilFile = new File(homeDirPath + SEP + "util.js");
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(jsTableStatFile, false)));
        out.println(this.constF.getTableStatFileCntx());
        out.close();
        
        out = new PrintWriter(new BufferedWriter(new FileWriter(jsUtilFile, !jsUtilFile.exists())));
        out.println(Constants.utilFileCntx);
        out.close();
        
        out = new PrintWriter(new BufferedWriter(new FileWriter(htmlFile, !htmlFile.exists())));
        out.println(Constants.htmlFileCntx);
        out.close();
        
        return htmlFile;
        
    }
}
