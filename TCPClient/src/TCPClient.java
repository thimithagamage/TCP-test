import java.io.*;
import java.net.*;

class TCPClient {
    String host = "localhost";
    int port = 6080;
    Socket socket = null;

    public static void main(String args[]) throws Exception {
        TCPClient client = new TCPClient();

        //create binary data
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
        outToServer.print(msg);
        outToServer.flush();
    }

    void close() throws IOException {
        socket.close();
    }
}

