import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {

            Socket socket = null;
            socket = welcomeSocket.accept();

            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte[] bytes = new byte[1024];

            in.read(bytes);
            System.out.println("Received From Client: " + bytes);
            String message =
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                            "<soapenv:Header/>\n" +
                            "<soapenv:Body>\n" +
                            "  <p:greet xmlns:p=\"http://greet.service.amani.org\">\n" +
                            "     <in>in the flow</in>\n" +
                            "  </p:greet>\n" +
                         "</soapenv:Body>\n" + "</soapenv:Envelope>";

            out.writeBytes(message);
            //out.write(bytes);
            out.flush();

        }
    }
}