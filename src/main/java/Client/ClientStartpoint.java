package Client;

import java.io.*;
import java.net.Socket;

public class ClientStartpoint {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;
    private Message messageObj;
    private ClientHandler handler;
    private BufferedReader consoleReader;
    private PrintWriter writer;
    private Socket socket;
    private String username;
    private String messageJson;

    public void start() {
        try {
            socket = new Socket(IP, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            String validName;

            System.out.println("Введите имя");
            while (true) {
                username = readerFromConsole();
                printToServer(username);
                validName = reader.readLine();

                if (validName.equals("valid")) {
                    break;
                }
                if (!validName.equals("valid")){
                    System.out.println(validName);
                }

            }

            handler = new ClientHandler(socket);
            handler.start();

            while (true) {
                String message = readerFromConsole();

                if (message == null) {
                    break;
                } else if (message.equals("exit")) {
                    printToServer(message);
                    break;
                } else {
                    messageObj = stringToObj(message);
                    messageJson = handler.toJson(messageObj);
                    printToServer(messageJson);

                }
            }
            handler.stopHandler();
            writer.close();
            socket.close();
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }

    public Message stringToObj(String message) {
        String[] s = message.split("@", 3);
        messageObj = new Message(username, s[1], s[2]);
        return messageObj;
    }

    public String readerFromConsole() {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void printToServer(String str) {
        try {
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
