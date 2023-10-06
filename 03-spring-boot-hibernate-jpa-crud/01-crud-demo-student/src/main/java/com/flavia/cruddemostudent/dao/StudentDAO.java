package com.flavia.cruddemostudent.dao;

import com.flavia.cruddemostudent.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();


}
