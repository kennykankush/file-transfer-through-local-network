import java.io.*;
import java.net.*;
import java.util.*;
import util.Writer;

public class Client {

    public static final String ARG_MSG = "<IP> <PORT>";
    static String hostname = "user";

    public static void main(String[] args) throws IOException {

        if (args.length > 2){
            System.out.println(ARG_MSG);
            return;
        }

        String IP = args[0];
        String PORT =  args[1];
        String fileName = "";
        
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
        }

        Socket clientSock = new Socket(IP, Integer.parseInt(PORT));
        System.out.println("You have connnected to: " + IP + " " + PORT);
        
        InputStream is = clientSock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = clientSock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Scanner scanner = new Scanner(System.in);
        String outgoing = "";
        String incoming = "";

        System.out.println("Welcome to the server that can only do two things," + hostname + "!");
        System.out.println("To bruteforce permutation, type bruteforce <string> <iteration>");
        System.out.println("To check what file is available, type 'check file'");
        System.out.println("To fetch a file, type 'fetch'. You be prompted again on what file to download");

        while (!outgoing.equals("quit")){

            outgoing = scanner.nextLine();

            dos.writeUTF(outgoing);
            dos.flush();

            incoming = dis.readUTF();
            System.out.println(incoming);
            if (incoming.startsWith("Fetching file")){
                String[] dataLine = incoming.split(": ");
                fileName = dataLine[1];
                System.out.println(fileName);

                int fileSize = dis.readInt();
                byte[] data = new byte[fileSize];
                dis.readFully(data);
                Writer.fileWriter(data, fileName);
            
            }

        }

        scanner.close();
        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();
        clientSock.close();

    } 
    
}
