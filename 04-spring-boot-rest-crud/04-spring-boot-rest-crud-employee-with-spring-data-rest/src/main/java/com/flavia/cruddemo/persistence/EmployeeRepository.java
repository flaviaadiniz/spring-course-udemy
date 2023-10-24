package com.flavia.cruddemo.persistence;

import com.flavia.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members") //exposes /members as the endpoint for the repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
