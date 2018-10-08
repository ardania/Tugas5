/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Audio.java
 *
 * Created on 04 Okt 18, 8:34:30
 */
package Tugas5;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;
import sun.audio.*;

/**
 *
 * @author ACER
 */
public class Audio extends javax.swing.JFrame {

    private static int SCALE_DEFAULT;
    public static final int SERVICE_PORT = 13;
    private static String direktori;

    /** Creates new form Audio */
    public Audio() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        open = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Pilih Audio");

        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel2)
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(send)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(open)))
                        .addGap(64, 64, 64))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(open))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(send)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed

        String namagambar = direktori;
        try {
            String hostname = "localhost";
            Socket daytime = new Socket(hostname, SERVICE_PORT);
            OutputStream os = daytime.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println(namagambar);

            BufferedReader br1 = new BufferedReader(new InputStreamReader(daytime.getInputStream()));
            JOptionPane.showMessageDialog(null, br1.readLine());

            os.flush();
            os.close();
            daytime.close();
        } catch (IOException ioe) {
            System.out.println("Error " + ioe);
        }
}//GEN-LAST:event_sendActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        try {
            JFileChooser fc = new JFileChooser();

            fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "gif");
            int open = fc.showOpenDialog(this);
            if (open == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();// ambil file yang dipilih

                String nama = file.getName();
                jTextField1.setText(nama);
                direktori = "" + fc.getSelectedFile();
                InputStream in = new FileInputStream(direktori);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);

                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);

                jLabel3.setText("Audio Sedang Dimainkan . . .");
            }
        } catch (IOException io) {
            System.out.println("Error : " + io);
        }
}//GEN-LAST:event_openActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Audio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton open;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}
