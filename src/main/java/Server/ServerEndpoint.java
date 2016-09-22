package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEndpoint {
    private static final int PORT = 8080;
    private ServerSocket serverSocket;
    private ClientManager clientManager = ClientManager.getInstance();

    /**
     * Создание сокета сервера. Запуск прослушки клиентов в main-thread
     */
    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket client = serverSocket.accept();
                clientManager.onClientConnected(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
