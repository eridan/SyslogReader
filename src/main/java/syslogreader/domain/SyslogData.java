package syslogreader.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SyslogData {
    
    private DateFormat dfDate = new SimpleDateFormat("DD/MM/yy");
    private DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
    private Calendar date;
    private String protocol;
    private String remoteIP;
    private String remotePORT;
    private String localIP;
    private String localPORT;
    private String localMACADDR;
    private String domainName;

    public SyslogData(String unformattedMsg) {
        setDate(unformattedMsg);
        setOtherFields(unformattedMsg);
    }

    public SyslogData(String protocol, String remoteIP, String remotePORT, String localIP, String localPORT, String localMACADDR) {
        this.protocol = protocol;
        this.remoteIP = remoteIP;
        this.remotePORT = remotePORT;
        this.localIP = localIP;
        this.localPORT = localPORT;
        this.localMACADDR = localMACADDR;
    }

        
    
    public Calendar getDate() {
        return date;
    }

    private void setDate(String unformattedMsg) {
        date = Calendar.getInstance();
        int curYear = 0;
        date.setTime(new Date());
        curYear = date.get(Calendar.YEAR);
        // Jan 14 21:53:34
        String dateStr = unformattedMsg.substring(3, 18);
//        System.out.println("Msg Date: " + dateStr);

        DateFormat df = new SimpleDateFormat("MMM dd HH:mm:ss");
        try {
            date.setTime(df.parse(dateStr));
        } catch (ParseException ex) {
            Logger.getLogger(SyslogData.class.getName()).log(Level.SEVERE, null, ex);
        }

        date.set(Calendar.YEAR, curYear);
        df = new SimpleDateFormat("DD-MMM-yyyy HH:mm:ss");
//        System.out.println("Calendar date: "+df.format(date.getTime()));
        this.date = date;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getRemotePORT() {
        return remotePORT;
    }

    public void setRemotePORT(String remotePORT) {
        this.remotePORT = remotePORT;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public String getLocalPORT() {
        return localPORT;
    }

    public void setLocalPORT(String localPORT) {
        this.localPORT = localPORT;
    }

    public String getLocalMACADDR() {
        return localMACADDR;
    }

    public void setLocalMACADDR(String localMACADDR) {
        this.localMACADDR = localMACADDR;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    
    
    // TODO: Refactor
    private void setOtherFields(String unformattedMsg) {
        String[] msgFields = unformattedMsg.substring(19, unformattedMsg.length()).split(" ");
        for (String field : msgFields) {
            int ind = field.indexOf("=")+1;
            if(field.startsWith("MAC")) {
                this.localMACADDR = field.substring(ind, field.length());
            }
            if(field.startsWith("SRC")) {
                this.localIP = field.substring(ind, field.length());
            }
            if(field.startsWith("DST")) {
                this.remoteIP = field.substring(ind, field.length());
            }
            if(field.startsWith("PROTO")) {
                this.protocol = field.substring(ind, field.length());
            }
            if(field.startsWith("SPT")) {
                this.localPORT = field.substring(ind, field.length());
            }
            if(field.startsWith("DPT")) {
                this.remotePORT = field.substring(ind, field.length());
            }
        }
    }

    @Override
    public String toString() {
        return "SyslogData{" + "date=" + dfDate.format(date.getTime()) + ", protocol=" + protocol + ", remoteIP=" + remoteIP + ", remotePORT=" + remotePORT + ", localIP=" + localIP + ", localPORT=" + localPORT + ", localMACADDR=" + localMACADDR + '}';
    }
    
    public String outputIntoFile() {
        return "{\"date\":\""+dfDate.format(date.getTime())+"\""
                +",\"time\":\""+dfTime.format(date.getTime())+"\""
                +",\"localIP\":\""+localIP+"\""
                +",\"destDomain\":\""+domainName+"\"}";
    }
    
    private Map<String,String> getFieldsAsMap() {
        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("localIP", this.localIP);
        fieldMap.put("localPORT", this.localPORT);
        fieldMap.put("localMACADDR", this.localMACADDR);
        fieldMap.put("protocol", this.protocol);
        fieldMap.put("remoteIP", this.remoteIP);
        fieldMap.put("remotePORT", this.remotePORT);
        return fieldMap;
    }


    // TODO: Refactor
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SyslogData other = (SyslogData) obj;
        
        // Loop through all fields - values and if match found - They're the same
        for (String fieldName : this.getFieldsAsMap().keySet()) {
            if(fieldName.equalsIgnoreCase("protocol") && other.getFieldsAsMap().containsKey(fieldName)) {
                if(!doValuesMatch(other, fieldName)) return false;
            } else if(fieldName.equalsIgnoreCase("localIP") && other.getFieldsAsMap().containsKey(fieldName)) {
                if(!doValuesMatch(other, fieldName)) return false;
            } else if(fieldName.equalsIgnoreCase("remotePORT") && other.getFieldsAsMap().containsKey(fieldName)) {
                    if(fieldName.equalsIgnoreCase("remotePORT")) {
                        System.out.println(this.getFieldsAsMap().get(fieldName)+" vs "+other.getFieldsAsMap().get(fieldName));
                    }
                if(!doValuesMatch(other, fieldName)) return false;
            }
        }
        
        return true;
    }

    private boolean doValuesMatch(final SyslogData other, String fieldName) {
        if (this.getFieldsAsMap().get(fieldName).equalsIgnoreCase(other.getFieldsAsMap().get(fieldName))) {
            return true;
        }
        return false;
    }

}
