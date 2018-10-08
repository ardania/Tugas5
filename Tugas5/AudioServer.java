/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

import sun.audio.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import javax.swing.*;

public class AudioServer {

    public static final int SERVICE_PORT = 13;
    public static String namagambar;

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            try {
                ServerSocket ss = new ServerSocket(SERVICE_PORT);
                System.out.println("Daytime service started");
                for (;;) {
                    Socket nextClient = ss.accept();
                    System.out.println("Received request from :\n" + "Alamat  : " + nextClient.getInetAddress() + "\nPort    : " + nextClient.getPort());

                    BufferedReader nama = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                    namagambar = nama.readLine();

                    System.out.println("Audio   : " + namagambar);

                    OutputStream os = nextClient.getOutputStream();
                    PrintStream ps = new PrintStream(os);
                    ps.println("Data Terkirim");

                    

                    os.flush();
                    os.close();
                    run = false;
                    nextClient.close();AudioServer as = new AudioServer();
                    as.putar();
                }
            } catch (BindException be) {
                System.out.println("Error " + be);
            } catch (IOException ioe) {
                System.out.println("Error " + ioe);
            }
        }
    }

    void putar() throws IOException {
        try {
            File sourcefile = new File(namagambar);
            InputStream in = new FileInputStream(sourcefile);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (IOException io) {
            System.out.println( io);
        }
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setText("Audio Sedang Dimainkan . . ");
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
}

