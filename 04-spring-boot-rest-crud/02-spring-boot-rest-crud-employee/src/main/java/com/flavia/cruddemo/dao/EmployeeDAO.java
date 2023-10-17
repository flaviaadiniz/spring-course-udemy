package com.flavia.cruddemo.dao;

import com.flavia.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

}
