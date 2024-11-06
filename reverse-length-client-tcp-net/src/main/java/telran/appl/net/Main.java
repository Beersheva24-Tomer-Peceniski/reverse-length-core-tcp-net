package telran.appl.net;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import telran.net.TcpClient;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class Main {
    static TcpClient client;

    public static void main(String[] args) {
        Item[] items = {
                Item.of("start session", Main::startSession),
                Item.of("exit", Main::exit, true)
        };
        Menu menu = new Menu("Echo Application", items);
        menu.perform(new StandardInputOutput());
    }

    static void startSession(InputOutput io) {
        String host = io.readString("Enter hostname");
        int port = io.readNumberRange("Enter port", "Wrong port", 3000, 50000).intValue();
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client = new TcpClient(host, port);
        Menu menu = new Menu("Run Session",
                Item.of("session", Main::sessionProcessing),
                Item.ofExit());
        menu.perform(io);
    }

    static void sessionProcessing(InputOutput io) {
        HashSet<String> types = new HashSet<>(List.of("reverse", "length"));
        String requestType = io.readStringOptions("Enter operation type " + types, "Wrong type", types);
        String requestData = io.readString("Enter any string");
        String response = client.sendAndReceive(requestType, requestData);
        io.writeLine(response);
    }

    static void exit(InputOutput io) {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
