package com.example.app.dao;

import com.example.app.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee save(Employee employee);

    Employee update(Employee employee);

    boolean deleteById(long id);

    Employee findById(long id);

    List<Employee> findAll();
}
