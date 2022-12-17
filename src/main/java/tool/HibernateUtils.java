package tool;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
