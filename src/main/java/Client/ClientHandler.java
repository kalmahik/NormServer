package Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;
import java.net.Socket;

public class ClientHandler extends Thread {

    private BufferedReader reader;
    private boolean alive = true;
    private Gson gson;


    public ClientHandler(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (alive) {

                String messageJson = reader.readLine();
                if (messageJson == null) {
                    break;
                }
                Message messageObj = fromJson(messageJson);

                System.out.println("Sender: " + messageObj.getSender());
                System.out.println("Message: " + messageObj.getMessage());

            }
            stopHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stopHandler() {
        if (alive && isAlive()) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            alive = false;
        }
    }

    public String toJson(Message messageObj) {
        gson = new GsonBuilder()
                .create();
        return gson.toJson(messageObj);
    }

    public Message fromJson(String messageJson) {
        gson = new Gson();
        return gson.fromJson(messageJson, Message.class);
    }
}
