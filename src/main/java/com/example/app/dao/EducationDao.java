package com.example.app.dao;

import com.example.app.model.Education;

import java.util.List;

public interface EducationDao {
    Education save(Education education);

    Education update(Education education);

    boolean deleteById(long id);

    Education findById(long id);

    List<Education> findAllByEmployeeId(long employeeId);
}
