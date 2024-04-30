package com.example.Tiktok20.DAO;

import com.example.Tiktok20.Database.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    Student getById(int id);

    List<Student> getAll();

    List<Student> getByName(String name);

    public Student update(Student student);
    public int updateAllName(String name);
}
