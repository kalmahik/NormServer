package Server;
import Server.entities.Message;

import java.util.ArrayList;


public class SendingThread extends Thread {
    private volatile ArrayList<Message> queue; //TODO: <Message>
    private boolean alive = true;

    public SendingThread() {
        queue = new ArrayList<>();
    }

    @Override
    public void run() {
        while (alive) {
            if (queue.isEmpty()) {
                Thread.yield();
            } else if (alive){
                ClientManager.getInstance().sendMessage(queue.get(0));
                queue.remove(0);
            }
        }
    }

    //TODO: message -> type Message
    public void addMessage(Message messageObj) {
        queue.add(messageObj);
    }

    public void stopSending() {
        alive = false;
    }
}
