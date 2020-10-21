package net.testjpa.springboot.Springbootjpacrudexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.testjpa.springboot.Springbootjpacrudexample.model.Employee;


@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
