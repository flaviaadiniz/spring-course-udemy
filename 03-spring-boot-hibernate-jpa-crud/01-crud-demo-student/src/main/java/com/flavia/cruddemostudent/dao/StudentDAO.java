package com.flavia.cruddemostudent.dao;

import com.flavia.cruddemostudent.entity.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);


}
