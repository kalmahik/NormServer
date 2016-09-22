package Server;

import Server.entities.Message;

import java.net.Socket;
import java.util.ArrayList;

public class ClientManager {
    private static ClientManager instance = new ClientManager();
    private ArrayList<Client> clients = new ArrayList<>();

    private ClientManager() {
    }

    public static ClientManager getInstance() {
        return instance;
    }

    /**
     * Событие подключения клиента
     *
     * @param socket - сокет клиента
     */
    public void onClientConnected(final Socket socket) {
        Thread thread = new Thread(() -> {
            Client client = new Client(socket);
            client.login();
        });
        thread.start();
    }

    /**
     * Событие окончания авторизации
     *
     * @param client авторизованный клиент
     */
    public void onClientSignedIn(Client client) {
        clients.add(client);
        System.out.println("Клиент " + client.getUsername() + " подключился");
        client.startMessaging();
    }

    /**
     * Событие отключения клиента
     *
     * @param client отключаемый клиент
     */
    public void onClientDisconnected(Client client) {
        if (clients.remove(client)) {
            System.out.println("Клиент " + client.getUsername() + " отключился");
        }
    }

    /**
     * Рассылка сообщений клиентам
     *
     * @param messageObj отправляемое сообщение //TODO: type -> Message
     */
    public void sendMessage(Message messageObj) {
        if (messageObj != null) {
            for (Client client : clients) {
                if (client.getUsername().equals(messageObj.getReceiver()))
                    client.sendMessage(messageObj);
                if (messageObj.getReceiver().equals("all")) {
                    if (!(client.getUsername().equals(messageObj.getSender()))) {
                        client.sendMessage(messageObj);
                    }
                }
            }
        }
    }


    public boolean hasClient(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
