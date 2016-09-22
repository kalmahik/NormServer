package Server.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateManager {
    public static  HibernateManager instance;
    private SessionFactory sessionFactory;

    private HibernateManager(){
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static synchronized HibernateManager getInstance(){
        if (instance == null){
            instance = new HibernateManager();
        }
        return instance;
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
