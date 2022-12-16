import controller.HibernateUtils;
import view.Home;

public class App {
    public static void main(String[] args) {
        var session = HibernateUtils.getSessionFactory();
        new Home("Videotron - Acceuil", 750, 500, session);
    }
}
