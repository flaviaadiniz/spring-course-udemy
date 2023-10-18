package com.flavia.cruddemo.dao;

import com.flavia.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;

    }

    @Override
    public Employee findById(int id) {

        // get employee
        Employee theEmployee = entityManager.find(Employee.class, id);

        // return employee
        return theEmployee;

    }

    @Override
    public Employee save(Employee employee) {

        // save employee
        Employee dbEmployee = entityManager.merge(employee); // .merge: if id == 0 then insert/save - else update

        // return the employee
        return dbEmployee;

    }

    @Override
    public void deleteById(int id) {

        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, id);

        // delete employee
        entityManager.remove(theEmployee);

    }

}
