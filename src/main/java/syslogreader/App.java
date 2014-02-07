package syslogreader;

import java.util.logging.Level;
import java.util.logging.Logger;
import syslogreader.domain.SyslogDataPortListener;
import syslogreader.ui.MainWindow;

public class App {

    private static int localPort = 514;
    private static MainWindow mainWindow;

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        mainWindow = new MainWindow();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
            }
        });

        // Testing. Outputing dummy messages, to mainWindow.messageReceived
//        final SyslogDataPortListener syslogListener = new SyslogDataPortListener(mainWindow, localPort);
//        Thread msgGenerator = new Thread(new Runnable() {
//
//            public void run() {
//                while (true) {
//                    try {
//                        String protocol = "UDP";
//                        String localIP = "77";
//                        String destPort = "80";
//                        short randomNo = (short)(Math.random()*2);
//                        System.out.println("Random: "+randomNo);
//                        syslogListener.messageReceived(
//                                "<4>Jan 23 10:53:34 kernel: [877853.270000] ACCEPT"
//                                + " IN=br0 OUT=eth1 MAC=1A:234:456 "
//                                + "SRC=192.168.0."+((randomNo==1)?"78":localIP)+" DST=173.252.110.27 LEN=134 TOS=0x00 "
//                                + "PREC=0x00 TTL=127 ID=30138 PROTO="+((randomNo!=1)?protocol:"TCP")+" SPT=23117 "
//                                + "DPT="+((randomNo!=1)?destPort:"5567")+" LEN=114");
//                        Thread.sleep(2000L);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        });
//        msgGenerator.start();
        
        
//         Start listening for incoming data
        
        SyslogDataPortListener syslogListener = new SyslogDataPortListener(mainWindow, localPort);
        Thread t = new Thread(syslogListener);
        t.start();
    }
}