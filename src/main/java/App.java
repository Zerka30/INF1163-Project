import controller.HibernateUtils;
import model.Service;
import view.Home;

public class App {
    public static void main(String[] args) {
        var session = HibernateUtils.getSessionFactory();
        new Home("Videotron", 500, 500, session);
     /*   var service = new Service(session);
        var movie = service.getMovie("55555555555555");
        System.out.println(service.getCategoriesFromMovie(movie));
*/
    }
}
