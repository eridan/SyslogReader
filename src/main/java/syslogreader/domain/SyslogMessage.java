package syslogreader.domain;

import java.util.Calendar;

public class SyslogMessage {
    
    private Calendar date,time;
    private String direction;
    private int protocol;
    private String remoteIP;
    private String remoteHOST;
    private int remotePORT;
    private String localIP;
    private int localPORT;
    private String macAddress;

    public SyslogMessage(Calendar date, Calendar time, String direction, int protocol, String remoteIP, String remoteHOST, int remotePORT, String localIP, int localPORT, String macAddress) {
        this.date = date;
        this.time = time;
        this.direction = direction;
        this.protocol = protocol;
        this.remoteIP = remoteIP;
        this.remoteHOST = remoteHOST;
        this.remotePORT = remotePORT;
        this.localIP = localIP;
        this.localPORT = localPORT;
        this.macAddress = macAddress;
    }

    public SyslogMessage() {
    }
    
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getRemoteHOST() {
        return remoteHOST;
    }

    public void setRemoteHOST(String remoteHOST) {
        this.remoteHOST = remoteHOST;
    }

    public int getRemotePORT() {
        return remotePORT;
    }

    public void setRemotePORT(int remotePORT) {
        this.remotePORT = remotePORT;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public int getLocalPORT() {
        return localPORT;
    }

    public void setLocalPORT(int localPORT) {
        this.localPORT = localPORT;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "SyslogMessage{" + "date=" + date + ", time=" + time + ", direction=" + direction + ", protocol=" + protocol + ", remoteIP=" + remoteIP + ", remoteHOST=" + remoteHOST + ", remotePORT=" + remotePORT + ", localIP=" + localIP + ", localPORT=" + localPORT + ", macAddress=" + macAddress + '}';
    }
}
