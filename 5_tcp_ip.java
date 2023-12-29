import java.util.*;
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 5000);
        System.out.print("Enter Filename : ");
        Scanner sc = new Scanner(System.in);
        String fname = sc.nextLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pWrite = new PrintWriter(ostream, true);
        pWrite.println(fname);

        InputStream istream = sock.getInputStream();
        Scanner filescan = new Scanner(new InputStreamReader(istream));
        while (filescan.hasNextLine()) {
            System.out.println(filescan.nextLine());
        }
        filescan.close();
        sc.close();
        sock.close();
        pWrite.close();
    }
}

import java.util.*;
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serSoc = new ServerSocket(5000);
        System.out.println("Server Established.");
        Socket socket = serSoc.accept();
        System.out.println("Connection Established. Waiting For Client..");

        InputStream istream = socket.getInputStream();
        Scanner sc = new Scanner(new InputStreamReader(istream));
        String fname = sc.nextLine();

        OutputStream ostream = socket.getOutputStream();
        PrintWriter pWrite = new PrintWriter(ostream, true);

        try {
            Scanner filescan = new Scanner(new FileReader(fname));
            while (filescan.hasNextLine()) {
                pWrite.println(filescan.nextLine());
            }
            filescan.close();
        } catch (FileNotFoundException e) {
            pWrite.println("File not found");
        } finally {
            System.out.println("Closing Connection");
            sc.close();
            socket.close();
            serSoc.close();
        }
    }
}
