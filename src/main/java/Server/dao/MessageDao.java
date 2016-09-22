package Server.dao;

import Server.entities.Message;

import java.util.List;

/**
 * Методы, которые должны быть в базе данных, для хранения сообщений
 */
public interface MessageDao {
    void add(Message message);

    Message get(int id);

    List<Message> getAll();

    Message remove(int id);
}


