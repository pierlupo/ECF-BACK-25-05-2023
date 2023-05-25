package org.example.service;

import org.example._interface.Repository;
import org.example.entity.Activity;
import org.example.entity.Member;
import org.hibernate.query.Query;

import java.util.List;

public class ActivityService extends BaseService implements Repository<Activity> {
    @Override
    public boolean create(Activity element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Activity element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Activity element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Activity findById(int id) {
        Activity activity = null;
        session = sessionFactory.openSession();
        activity = (Activity) session.get(Activity.class, id);
        session.close();
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> activityList = null;
        session = sessionFactory.openSession();
        Query<Activity> activityQuery = session.createQuery("from Activity");
        activityList = activityQuery.list();
        session.close();
        return activityList;
    }
}
