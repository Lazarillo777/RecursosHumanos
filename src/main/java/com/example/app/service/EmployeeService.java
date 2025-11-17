package com.example.app.service;

import com.example.app.dao.EmployeeDao;
import com.example.app.dao.impl.EmployeeDaoJdbc;
import com.example.app.model.Employee;

import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoJdbc();

    public Employee create(Employee e) {
        return employeeDao.save(e);
    }

    public Employee update(Employee e) {
        return employeeDao.update(e);
    }

    public boolean delete(long id) {
        return employeeDao.deleteById(id);
    }

    public Employee findById(long id) {
        return employeeDao.findById(id);
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
