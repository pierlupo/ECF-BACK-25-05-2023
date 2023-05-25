package org.example.service;

import org.example._interface.Repository;
import org.example.entity.Activity;
import org.example.entity.Member;
import org.hibernate.query.Query;

import java.util.List;

public class MemberService extends BaseService implements Repository<Member> {
    @Override
    public boolean create(Member element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Member element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Member element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Member findById(int id) {
        Member member = null;
        session = sessionFactory.openSession();
        member = (Member) session.get(Member.class, id);
        session.close();
        return member;
    }

    @Override
    public List<Member> findAll() {
        List<Member> memberList = null;
        session = sessionFactory.openSession();
        Query<Member> memberQuery = session.createQuery("from Member");
        memberList = memberQuery.list();
        session.close();
        return memberList;
    }

    public boolean addActivityToMember(Activity activity, int id) {
        boolean result = false;
        Member member = this.findById(id);
        session =sessionFactory.openSession();
        session.getTransaction().begin();
        if(member != null) {
            activity.addMember(member);
            session.save(activity);
            result = true;
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }
    public List<Member> getActivitiesByMemberId(int  id){
        session = sessionFactory.openSession();
        Query<Member> memberQuery = session.createQuery("select distinct name from Activity where id =:id");
        memberQuery.setParameter("id", id);
        List<Member> memberList = memberQuery.list();
        session.close();
        return memberList;
    }

    public void begin(){
        session = sessionFactory.openSession();
    }

    public void end(){
        sessionFactory.close();
    }
}
