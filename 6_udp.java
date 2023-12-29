import java.util.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DatagramSocket dataSock = new DatagramSocket(6000);
        byte[] buffer;
        String msg;
        System.out.println("Recived Message : ");
        while (true) {
            buffer = new byte[65555];
            DatagramPacket dataPack = new DatagramPacket(buffer, buffer.length);
            dataSock.receive(dataPack);
            msg = new String(buffer).trim();
            System.out.println(msg);
            if (msg.equalsIgnoreCase("exit")) {
                System.out.println("End Of Message");
                break;
            }
            sc.close();
            dataSock.close();
        }
    }
}

import java.util.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DatagramSocket datasock = new DatagramSocket();
        InetAddress clientAddr = InetAddress.getByName("0.0.0.0");
        DatagramPacket dataPack;
        byte[] buffer;
        System.out.println("Enter the Message : ");
        while (true) {
            String msg = sc.nextLine();
            buffer = msg.getBytes();
            dataPack = new DatagramPacket(buffer, buffer.length, clientAddr, 6000);
            datasock.send(dataPack);
            if (msg.equalsIgnoreCase("exit")) {
                System.out.println("THANK YOU");
                break;
            }
        }
        sc.close();
        datasock.close();
    }
}
