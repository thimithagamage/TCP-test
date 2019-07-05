import java.io.*;
import java.net.*;

class TCPClient {
    String host = "localhost";
    int port = 6080;
    Socket socket = null;

    public static void main(String args[]) throws Exception {
        TCPClient client = new TCPClient();

        String str= "hello world";
        byte[] message = str.getBytes();
        System.out.println(message.toString());
        client.SendToServer(message);
        client.close();
    }

    TCPClient(String _host, int _port) throws Exception {
        host = _host;
        port = _port;
        socket = new Socket(host, port);
    }

    TCPClient() throws Exception {
        socket = new Socket(host, port);
    }

    void SendToServer(byte[] msg) throws Exception {
        // create output stream attached to socket
        PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        // send msg to server
        System.out.println("To server from client "+msg);
        outToServer.print(msg);
        outToServer.flush();
    }

   /* String RecieveFromServer() throws Exception {
        // create input stream attached to socket
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        byte[] bytes = new byte[1024];

        in.read(bytes);
        // read line from server
        System.out.println(bytes);
        System.out.println("Received from server");
        // end, this throws
        // java.net.SocketException
        String res = bytes.toString();
        return res;
    }*/

    String RecieveFromServer() throws Exception {
        // create input stream attached to socket
        BufferedReader inFromServer =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        // read line from server
        String res = inFromServer.readLine(); // if connection closes on server
        // end, this throws
        // java.net.SocketException
        return res;
    }

    void close() throws IOException {
        socket.close();
    }
}

