package com.example.Tiktok20.DAO;

import com.example.Tiktok20.Database.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO{

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student getById(int id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        String jpql = "SELECT s FROM Student s";
        return this.entityManager.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    public List<Student> getByName(String name) {
        String jpql = "SELECT s FROM Student s WHERE s.name=:t";
        TypedQuery<Student> query = this.entityManager.createQuery(jpql, Student.class);
        query.setParameter("t", name);
        return  query.getResultList();
    }

    @Override
    public Student update(Student student) {
        return this.entityManager.merge(student);
    }

    @Override
    public int updateAllName(String name) {
        String jpql = "UPDATE Student s SET s.name=:t";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("t", name);
        return query.executeUpdate();
    }


}
