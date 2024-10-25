import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import util.Exhauster;
import util.fileManagement;

public class Server {

    public static final String ARG_MSG = "<port>";

    public static void main(String[] args) throws IOException{

        // if (args.length > 2){
        //     System.out.println(ARG_MSG);
        //     return;

        // }

        // String PORT = args[0];
        int PORT = 2412;
        System.out.println("Server has been initiated at port: " + PORT);

        ServerSocket serverSock = new ServerSocket(PORT);
        Socket sock = serverSock.accept();

        System.out.println("Client have connected");

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        String incoming = "";
        String outgoing = "";

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        while(!incoming.equalsIgnoreCase("quit")){
            incoming = dis.readUTF();

            if (incoming.startsWith("bruteforce")){
                String[] incomingData = incoming.split(" ");
                outgoing = Exhauster.start(incomingData[1], Integer.parseInt(incomingData[2]));
                System.out.println("Sending results to client: " + outgoing);
                dos.writeUTF(outgoing);
                
            } else if (incoming.equalsIgnoreCase("quit")){
                outgoing = "Bye bye!";
                dos.writeUTF(outgoing);
            } else if (incoming.equalsIgnoreCase("check file")){
                outgoing = fileManagement.checkFilesAvailable();
                dos.writeUTF(outgoing);
            } else if (incoming.equalsIgnoreCase("fetch")){
                outgoing = "Which file?";
                dos.writeUTF(outgoing);
                dos.flush();

                incoming = dis.readUTF();

                byte[] _package_ = fileManagement.byteCatcher(incoming);

                outgoing = "Fetching file: " + incoming;
                dos.writeUTF(outgoing);
                dos.writeInt(_package_.length);
                dos.write(_package_);                       //https://www.geeksforgeeks.org/java-program-to-write-bytes-using-bytestream/
            } else {
                outgoing = "Please type a proper input";
                dos.writeUTF(outgoing);
            }

            dos.flush();

        }

        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();
        sock.close();
        serverSock.close();;

    } 
    
}
