/**
 * Syslog Server Java: Multiplatform: Easy setup Syslog Server tool for
 * recording network messages. It opens the UDP port 514 on your computer to act
 * as Syslog Server and displays the messages immediately in your screen.
 *
 * Copyright (C) Julio Molina Soler <jmolinaso@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package syslogreader.domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import syslogreader.ui.MainWindow;

public class SyslogDataPortListener implements Runnable {

    private SyslogData syslogData;
    private SyslogFilter filter = new SyslogFilter();
    private MainWindow outputFrame;
    private int localPort = 8514;
    private boolean stopRequested;
    private DatagramSocket socket;
    private Thread runThread;
    
    

    public SyslogDataPortListener(MainWindow outputFrame, int localPort) {
        this.outputFrame = outputFrame;
        this.localPort = localPort;
    }

    public void run() {
        runThread = Thread.currentThread();
        stopRequested = false;
        try {
            socket = new DatagramSocket(localPort);
            this.outputFrame.addMsgToLiveTextArea("Service log started on port " + localPort);
        } catch (SocketException e) {
            this.outputFrame.addMsgToLiveTextArea("Can't start listening: " + e.getMessage());
        }
        try {
            while (!stopRequested) {
                DatagramPacket dato = new DatagramPacket(new byte[2048], 2048);
                socket.receive(dato);
                String msg = new String(dato.getData(), 0, dato.getLength());
//                                System.out.println(msg);
                messageReceived(msg);
            }

        } catch (IOException e) {
            this.outputFrame.addMsgToLiveTextArea(e.getMessage());
        }

    }

    public void stopRequest() {
        stopRequested = true;

        if (runThread != null) {
            socket.close();
            runThread.interrupt();
        }
    }

    public void messageReceived(String unformattedMsg) {
        this.outputFrame.addMsgToLiveTextArea(unformattedMsg);
        SyslogData syslogData = new SyslogData(unformattedMsg);
        if (isFilerApplied()) {
            syslogData = filter.validateMsg(syslogData);
        }
            this.outputFrame.addMsgToFilteredViewTable(syslogData == null ? null : syslogData);

//        } else {
//            this.outputFrame.addMsgToFilteredViewTable(syslogData);
//        }
    }

    private boolean isFilerApplied() {
        return this.outputFrame.getApplyFilterCheckBox().isSelected();
    }
}
