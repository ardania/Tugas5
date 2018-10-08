/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final int SERVICE_PORT = 13;

    public static void main(String[] args) {
        List<Mahasiswa> participants = new ArrayList<Mahasiswa>();
        String NIM, Nama, Asal, Kelas;
        SerializationDemo demo = new SerializationDemo();
        String direktori = "d:/DataMahasiwa.ser";
        boolean run = true;

        while (run) {
            try {
                ServerSocket ss = new ServerSocket(SERVICE_PORT);
                System.out.println("Daytime service started");
                for (;;) {
                    Socket nextClient = ss.accept();
                    System.out.println("Received request from :\n" + "Alamat  : " + nextClient.getInetAddress() + "\nPort    : " + nextClient.getPort());

                    BufferedReader nim = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                    NIM = nim.readLine();

                    BufferedReader nama = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                    Nama = nama.readLine();

                    BufferedReader asal = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                    Asal = asal.readLine();

                    BufferedReader kelas = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                    Kelas = kelas.readLine();

                    // simpan data ke file
                    participants.add(new Mahasiswa(NIM, Nama, Asal, Kelas));
                    System.out.println("Message : " + participants);

                    demo.serialize(participants, direktori);
                    System.out.println("Save Succesfull");


                    OutputStream os = nextClient.getOutputStream();
                    PrintStream ps = new PrintStream(os);
                    ps.println(participants);
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
}
