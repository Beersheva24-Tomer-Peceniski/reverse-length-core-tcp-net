package telran.appl.net;

import telran.net.*;

public class Main {
    public static void main(String[] args) {
        ReverseLengthProtocol protocol = new ReverseLengthProtocol();
        final int PORT = 4000;
        TcpServer tcpServer = new TcpServer(protocol, PORT);
        tcpServer.run();
    }
}