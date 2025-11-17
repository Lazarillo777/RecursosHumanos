package com.example.app.dao;

import com.example.app.model.Phone;

import java.util.List;

public interface PhoneDao {
    Phone save(Phone phone);

    Phone update(Phone phone);

    boolean deleteById(long id);

    Phone findById(long id);

    List<Phone> findAllByEmployeeId(long employeeId);
}
