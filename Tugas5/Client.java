/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

/**
 *
 * @author ACER
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final int SERVICE_PORT = 13;

    public static void main(String[] args) {
        String NIM, Nama, Asal, Kelas;
        try {
            String hostname = "localhost";
            Socket daytime = new Socket(hostname, SERVICE_PORT);
            System.out.println("Connection Established");
            int a=2000;
            daytime.setSoTimeout(a);
            Scanner input = new Scanner(System.in);
            OutputStream os = daytime.getOutputStream();
            PrintStream ps = new PrintStream(os);

            System.out.println("==================================");
            System.out.println("     Masukkan Data Mahasiswa ");
            System.out.print("NIM             : ");
            NIM = input.next();
            ps.println(NIM);

            System.out.print("Nama            : ");
            Nama = input.next();
            ps.println(Nama);

            System.out.print("Asal            : ");
            Asal = input.next();
            ps.println(Asal);

            System.out.print("Kelas Praktikum : ");
            Kelas = input.next();
            ps.println(Kelas);

            System.out.println("Send Successful");

            BufferedReader br1 = new BufferedReader(new InputStreamReader(daytime.getInputStream()));
            System.out.println("Data send : " + br1.readLine());
            System.out.println("");
            os.flush();
            os.close();

            daytime.close();

        } catch (IOException ioe) {
            System.out.println("Error : " + ioe);
        }
    }
}
