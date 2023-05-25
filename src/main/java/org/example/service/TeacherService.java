package org.example.service;

import org.example._interface.Repository;
import org.example.entity.Activity;
import org.example.entity.Teacher;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherService extends BaseService implements Repository<Teacher> {
    @Override
    public boolean create(Teacher element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Teacher element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Teacher element) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(element);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Teacher findById(int id) {
        Teacher teacher = null;
        session = sessionFactory.openSession();
        teacher = (Teacher) session.get(Teacher.class, id);
        session.close();
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teacherList = null;
        session = sessionFactory.openSession();
        Query<Teacher> teacherQuery = session.createQuery("from Teacher");
        teacherList = teacherQuery.list();
        session.close();
        return teacherList;
    }
}
