package com.payroll.spring_rest_tutorial.repository;

import com.payroll.spring_rest_tutorial.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
