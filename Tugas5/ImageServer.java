/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class ImageServer {

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

                    System.out.println("Image   : " + namagambar);

                    OutputStream os = nextClient.getOutputStream();
                    PrintStream ps = new PrintStream(os);
                    ps.println("Data Terkirim");

                    ImageServer is = new ImageServer();
                    is.tampil();

                    os.flush();
                    os.close();
                    run = false;
                    nextClient.close();
                }
            } catch (BindException be) {
                System.out.println("Error " + be);
            } catch (IOException ioe) {
                System.out.println("Error " + ioe);
            }
        }
    }

    void tampil() throws IOException {
        BufferedImage image = null;
        try {
            File sourcefile = new File(namagambar);
            image = ImageIO.read(sourcefile);

            InputStream is = new BufferedInputStream(new FileInputStream(sourcefile));
            image = ImageIO.read(is);
        } catch (IOException io) {
            System.out.println("Error " + io);
        }
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
