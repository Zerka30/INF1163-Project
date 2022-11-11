package service;

import model.Member;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class MemberService {
    private final SessionFactory sessionFactory;

    public MemberService(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public void addMember(Member member) {
        Objects.requireNonNull(member);
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.save(member);
        transaction.commit();
        session.close();
    }
}
