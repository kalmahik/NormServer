package Server.dao;

import Server.entities.Message;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MessageDatabase implements MessageDao {
    private static final MessageDatabase instance = new MessageDatabase();

    private MessageDatabase() {

    }

    public static MessageDatabase getInstance() {
        return instance;
    }

    @Override
    public void add(Message message) {

        Session session = HibernateManager.getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Message get(int id) {
        Session session = HibernateManager.getInstance()
                .getSessionFactory()
                .openSession();
        Message message = (Message) session.createCriteria(Message.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return message;
    }

    @Override
    public List<Message> getAll() {
        Session session = HibernateManager.getInstance()
                .getSessionFactory()
                .openSession();
        List<Message> messages = session.createCriteria(Message.class).list();
        session.close();
        return messages;
    }

    @Override
    public Message remove(int id) {
        Session session = HibernateManager.getInstance()
                .getSessionFactory()
                .openSession();
        Message m = get(id);
        session.delete(m);
        session.getTransaction().commit();
        session.close();
        return m;
    }

    public List<Message> getHistory(String username) {
        Session session = HibernateManager.getInstance()
                .getSessionFactory()
                .openSession();
        List<Message> messages = session.createCriteria(Message.class)
                .add(Restrictions.or(
                        Restrictions.eq("receiver", username),
                        Restrictions.eq("sender", username),
                        Restrictions.eq("receiver", "all")))
                .list();
        session.close();
        return messages;
    }
}
