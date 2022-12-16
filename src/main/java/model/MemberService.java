package model;

import entity.Member;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.InjectService;

import javax.persistence.Entity;
import java.util.Objects;

public class MemberService {
    private final SessionFactory sessionFactory;

    public MemberService(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    public void addMember(Object member) {
        if (member instanceof Entity)
            System.out.println("Ok, c'est une entité");
        Objects.requireNonNull(member);
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        session.save("member");
        transaction.commit();
        session.close();
    }
}
