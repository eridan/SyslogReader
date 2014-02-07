package syslogreader.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyslogFilter {

    private final String CONFIG_FILE = "/config/filter.txt";
    private final Logger LOGGER = Logger.getLogger(SyslogFilter.class.getName());
    private List<SyslogData> filterSyslogDataList = new ArrayList<SyslogData>();
    private List<String> protocol;
    private List<String> localIP;
    private String localPort;
    private String destIP;
    private List<String> destPort;
    private String localMACADDR;

    public SyslogFilter() {
        Properties prop = new Properties();
        File file = null;

        try {
//            String absolutePath = SyslogFilter.class.getClass().getResource(CONFIG_FILE).getPath();
//            file = new File(absolutePath);
//            InputStream stream = new FileInputStream(file);
//            prop.load(stream);
            prop.load(SyslogFilter.class.getClass().getResourceAsStream(CONFIG_FILE));
            protocol = Arrays.asList(prop.getProperty("protocol").split(","));
            localIP = Arrays.asList(prop.getProperty("localIP").split(","));
            localPort = prop.getProperty("localPort");
            destIP = prop.getProperty("destIP");
            destPort = Arrays.asList(prop.getProperty("destPort").split(","));
            localMACADDR = prop.getProperty("localMACADDR");

//            filterSyslogDataList = new SyslogData(protocol, destIP, destPort, localIP, localPort, localMACADDR);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Can not read {0}", file.getAbsolutePath());
            LOGGER.log(Level.SEVERE, "Error: {0}", ex.getMessage());
        }
    }

    public SyslogData validateMsg(SyslogData syslogData) {
        if(!protocol.contains(syslogData.getProtocol())) {
            return null;
        } else if(!localIP.contains(syslogData.getLocalIP())) {
            return null;
        } else if(!destPort.contains(syslogData.getRemotePORT())) {
            return null;
        }
        
        return syslogData;
        
//        if(syslogData.equals(filterSyslogDataList)) {
//            return syslogData;
//        }
//        return null;
    }
}
